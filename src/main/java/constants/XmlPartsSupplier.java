package constants;

import org.apache.commons.lang3.StringUtils;

import domain.AddColumn;
import domain.AddNotNullConstraint;
import domain.Column;
import domain.CreateIndex;
import domain.CreateTable;
import domain.DropColumn;
import domain.DropIndex;
import domain.DropNotNullConstraint;
import domain.GeneralColumn;
import domain.GeneralTable;
import domain.HasIndex;
import generator.Generator;
import generator.GeneratorSettings;
import main.LBJ;

/**
 * Supplies {@link Generator} with {@link XmlParts}. Also does replacements of
 * names, ids, author and so on.
 */
public class XmlPartsSupplier {

	private XmlPartsSupplier() {
		// only static methods
	}

	/**
	 * Finishes {@link Generator} job according to {@link GeneratorSettings} like
	 * putting changesets into changelog or leaving them be and replacing author and
	 * IDs.
	 */
	public static String finish(String changesets, GeneratorSettings settings) {
		if (settings.isOnlyChangeSets()) {
			return XmlPartsSupplier.toChangeSets(changesets, settings);
		}
		return XmlPartsSupplier.toChangelog(changesets, settings);
	}

	/**
	 * Replaces author and ID of each changeset with incremental number starting
	 * from 1 and puts all of them into changelog.
	 */
	private static String toChangelog(String changesets, GeneratorSettings settings) {
		String changelog = StringUtils.replace(XmlParts.getChangelogStart(), XmlParts.REPLACE_CHAGESETS, changesets);
		changelog = XmlPartsSupplier.replaceAuthor(changelog, settings.getAuthor());
		changelog = replaceIds(changelog, 1);
		changelog = XmlPartsSupplier.replaceVersion(changelog);
		return changelog;
	}

	/**
	 * Replaces author and ID of each changeset with incremental number starting
	 * from passed argumend
	 */
	private static String toChangeSets(String changesets, GeneratorSettings settings) {
		changesets = StringUtils.replace(XmlParts.getChangesetsStart(), XmlParts.REPLACE_CHAGESETS, changesets);
		changesets = XmlPartsSupplier.replaceAuthor(changesets, settings.getAuthor());
		changesets = replaceIds(changesets, settings.getStartingId());
		changesets = XmlPartsSupplier.replaceVersion(changesets);
		return changesets;
	}

	private static String replaceIds(String changesets, int startingId) {
		while (StringUtils.contains(changesets, XmlParts.REPLACE_ID)) {
			changesets = replaceId(changesets, startingId);
			startingId++;
		}
		return changesets;
	}

	private static String replaceAuthor(String replaceIn, String author) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_AUTHOR, author);
	}

	/**
	 * Also used from tests where you need to replace version in tests files to be
	 * in line with actually generated files
	 */
	public static String replaceVersion(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_LBJ_VERSION, LBJ.properties.getVersion());
	}

	private static String replaceId(String replaceIn, int id) {
		return StringUtils.replaceOnce(replaceIn, XmlParts.REPLACE_ID, String.valueOf(id));
	}

	public static String replaceTableName(String replaceIn, String tableName) {
		replaceIn = StringUtils.replace(replaceIn, XmlParts.REPLACE_TABLE_NAME_LOWER_CASE,
				StringUtils.lowerCase(tableName));
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_TABLE_NAME, tableName);
	}

	public static String replaceColumnPrimaryKeyName(String replaceIn, CreateTable table) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_PRIMARY_KEY_NAME,
				table.getPrimaryKeyColumnName());
	}

	public static String replaceConstrainPrimaryKeyName(String replaceIn, CreateTable table) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_CONSTRAIN_PRIMARY_KEY_NAME,
				table.getPrimaryKeyContrainName());
	}

	public static String replaceSequenceName(String replaceIn, GeneralTable table) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_SEQUENCE_NAME, table.getSequenceName());
	}

	public static String replaceColumnName(String replaceIn, GeneralColumn column) {
		replaceIn = StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NAME_UPPER_CASE,
				StringUtils.upperCase(column.getName()));
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NAME, column.getName());
	}

	public static String replaceColumnDataType(String replaceIn, GeneralColumn column) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_DATA_TYPE, column.getDataType());
	}

	public static String replaceColumnForeignKeyName(String replaceIn, Column column) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_NAME,
				column.getForeignKey().getName());
	}

	public static String replaceColumnForeignKeyReferencedTable(String replaceIn, Column column) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME,
				column.getForeignKey().getReferencedTable());
	}

	public static String replaceColumnForeignKeyReferencedColumn(String replaceIn, Column column) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_REFERECED_COLUMN_NAME,
				column.getForeignKey().getReferencedColumn());
	}

	public static String replaceColumnIndexName(String replaceIn, HasIndex column) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_INDEX_NAME, column.getIndexName());
	}

	public static String replaceColumnNullable(String replaceIn, AddColumn column) {
		if (column.isNullable()) {
			return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NULLABLE, "");
		}
		replaceIn = StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NULLABLE,
				XmlParts.getAddColumnConstraintsNullable());
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NULLABLE_VALUE, "false");
	}

	public static String addColumnConstraintsBase(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_CONSTRAINS, XmlParts.getAddColumnConstraints());
	}

	public static String removeColumnForeignKeyConstraint(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY, "");
	}

	public static String removeConstraints(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_CONSTRAINS, "");
	}

	public static String addColumnConstraintsForeignKey(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY,
				XmlParts.getAddColumnConstraintsForeignKey());
	}

	public static String getTableBase(CreateTable table) {
		String xmlTable = "";
		if (table.isForMssql()) {
			xmlTable += XmlParts.getCreateTableMssql();
		}
		if (table.isForOracle() || table.isForPostgre()) {
			xmlTable += XmlParts.getCreateTableOraclePostgre();
			if (table.isForOracle()) {
				xmlTable += XmlParts.getCreateTableSequenceOracle();
			}
			if (table.isForPostgre()) {
				xmlTable += XmlParts.getCreateTableSequencePostgre();
			}
		}
		return xmlTable;
	}

	public static String getDropTableBase() {
		return XmlParts.getDropTableBase();
	}

	public static String getDropSequenceBase() {
		return XmlParts.getDropSequenceBase();
	}

	public static String getModifyDataTypeBase() {
		return XmlParts.getModifyDataTypeBase();
	}

	public static String getAddColumnBase(AddColumn column) {
		if (column.isTypeBoolean()) {
			return getBooleanColumnBase(column);
		}
		return XmlParts.getAddGeneralColumnBase();
	}

	public static String getDropForeignKeyConstraintBase() {
		return XmlParts.getDropForeignKeyContraint();
	}

	public static String getAddForeignKeyConstraintBase() {
		return XmlParts.getAddForeignKeyConstraint();
	}

	public static String getDropColumnBase() {
		return XmlParts.getDropColumnBase();
	}

	public static String getDropIndexBase(DropIndex column) {
		String dropIndex = "";
		if (column.isForMssql() || column.isForPostgre()) {
			dropIndex += XmlParts.getDropIndexMssqlPostgre();
		}
		if (column.isForOracle()) {
			dropIndex += XmlParts.getDropIndexOracle();
		}
		return dropIndex;
	}

	private static String getBooleanColumnBase(AddColumn booleanColumn) {
		String xmlColumn = "";
		if (booleanColumn.isForOracle() || booleanColumn.isForMssql()) {
			xmlColumn = XmlParts.getBooleanCollumnOracleMssql();
		}
		if (booleanColumn.isForPostgre()) {
			xmlColumn += XmlParts.getBooleanColumnPostgre();
		}
		return xmlColumn;
	}

	public static String getColumnIndexBase(CreateIndex column) {
		String xmlIndex = "";
		if (column.isForMssql() || column.isForPostgre()) {
			xmlIndex += XmlParts.getColumnIndexMssqlPostgre();
		}
		if (column.isForOracle()) {
			xmlIndex += XmlParts.getColumnIndexOracle();
		}
		return xmlIndex;
	}

	public static String getDropNotNullConstraintBase(DropNotNullConstraint constraint) {
		String xmlConstraint = "";
		if (constraint.isForOracle()) {
			xmlConstraint += XmlParts.getDropNotNullConstraintOracle();
		}
		if (constraint.isForMssql()) {
			xmlConstraint += XmlParts.getDropNotNullConstraintMssql();
		}
		if (constraint.isForPostgre()) {
			xmlConstraint += XmlParts.getDropNotNullConstraintPostgre();
		}
		return xmlConstraint;
	}

	public static String getAddNotNullConstraintBase(AddNotNullConstraint constraint) {
		String xmlConstraint = "";
		if (constraint.isForOracle()) {
			xmlConstraint += XmlParts.getAddNotNullConstraintOracle();
		}
		if (constraint.isForMssql()) {
			xmlConstraint += XmlParts.getAddNotNullConstraintMssql();
		}
		if (constraint.isForPostgre()) {
			xmlConstraint += XmlParts.getAddNotNullConstraintPostgre();
		}
		return xmlConstraint;
	}

	public static String replaceColumnDefaultValueBase(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_DEFAULT_VALUE_BASE,
				XmlParts.getAddColumnDefaultValue());
	}

	public static String replaceColumnDefaultValue(String replaceIn, AddColumn column) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_DEFAULT_VALUE, column.getDefaultValue());
	}

	public static String removeColumnDefaultValueBase(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_DEFAULT_VALUE_BASE, "");
	}

	public static String replaceColumnDefaultValueOracleMssql(String replaceIn, AddColumn column) {
		String defaultValue = null;
		if (StringUtils.equals(column.getDefaultValue(), "1")
				|| StringUtils.equalsIgnoreCase(column.getDefaultValue(), "true")) {
			defaultValue = "1";
		} else if (StringUtils.equals(column.getDefaultValue(), "0")
				|| StringUtils.equalsIgnoreCase(column.getDefaultValue(), "false")) {
			defaultValue = "0";
		} else {
			throw new IllegalArgumentException(
					"Invalid default value for column of type boolean, valid values are 0, 1, true and false. Your value: "
							+ column.getDefaultValue());
		}
		return StringUtils.replaceOnce(replaceIn, XmlParts.REPLACE_COLUMN_DEFAULT_VALUE, defaultValue);
	}

	public static String replaceColumnDefaultValuePostgre(String replaceIn, AddColumn column) {
		String defaultValue = null;
		if (StringUtils.equals(column.getDefaultValue(), "1")
				|| StringUtils.equalsIgnoreCase(column.getDefaultValue(), "true")) {
			defaultValue = "true";
		} else if (StringUtils.equals(column.getDefaultValue(), "0")
				|| StringUtils.equalsIgnoreCase(column.getDefaultValue(), "false")) {
			defaultValue = "false";
		} else {
			throw new IllegalArgumentException(
					"Invalid default value for column of type boolean, valid values are 0, 1, true and false. Your value: "
							+ column.getDefaultValue());
		}
		return StringUtils.replaceOnce(replaceIn, XmlParts.REPLACE_COLUMN_DEFAULT_VALUE, defaultValue);
	}

	public static String replaceDropColumnDefaultValue(String xmlDropColumn, DropColumn column) {
		String replaceWith = "";
		if (column.hasDefaultValue()) {
			replaceWith = XmlParts.getDropColumnDefaultValue();
		}
		return StringUtils.replace(xmlDropColumn, XmlParts.REPLACE_DROP_COLUMN_DEFAULT_VALUE, replaceWith);
	}

}
