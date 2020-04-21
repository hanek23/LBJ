package constants;

import org.apache.commons.lang3.StringUtils;

import domain.AddColumn;
import domain.Column;
import domain.CreateTable;
import domain.DropColumn;
import domain.DropNotNullConstraint;
import domain.GeneralColumn;
import domain.GeneralTable;
import generator.Generator;
import generator.GeneratorSettings;

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
		changesets = XmlPartsSupplier.replaceAuthor(changesets, settings.getAuthor());
		changesets = replaceIds(changesets, 1);
		return StringUtils.replace(XmlParts.getChangelogStart(), XmlParts.REPLACE_CHAGESETS, changesets);
	}

	/**
	 * Replaces author ID of each changeset with incremental number starting from
	 * passed argumend
	 */
	private static String toChangeSets(String changesets, GeneratorSettings settings) {
		changesets = XmlPartsSupplier.replaceAuthor(changesets, settings.getAuthor());
		changesets = replaceIds(changesets, settings.getStartingId());
		return StringUtils.replace(XmlParts.getChangesetsStart(), XmlParts.REPLACE_CHAGESETS, changesets);
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

	public static String replaceColumnIndexName(String replaceIn, Column column) {
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

	public static String getAddColumnBase(AddColumn column) {
		if (column.isTypeBoolean()) {
			return getBooleanColumnBase(column);
		}
		return XmlParts.getAddGeneralColumnBase();
	}

	public static String getDropForeignKeyBase() {
		return XmlParts.getDropForeignKey();
	}

	public static String getDropColumnBase() {
		return XmlParts.getDropColumnBase();
	}

	public static String getDropIndexBase(DropColumn column) {
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

	public static String getColumnIndexBase(AddColumn column) {
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

}
