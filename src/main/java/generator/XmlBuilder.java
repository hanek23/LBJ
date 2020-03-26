package generator;

import org.apache.commons.lang3.StringUtils;

import constants.XmlParts;
import domain.AddColumn;
import domain.GeneralColumn;
import domain.Table;

public class XmlBuilder {

	private XmlBuilder() {
		// only static methods
	}

	/**
	 * Replaces ID of each changeset with incremental number starting from 1 and
	 * puts all of them into changelog.
	 */
	public static String toChangelog(String changesets) {
		changesets = replaceIds(changesets, 1);
		return StringUtils.replace(XmlParts.getChangelogStart(), XmlParts.REPLACE_CHAGESETS, changesets);
	}

	private static String replaceIds(String changesets, int startingId) {
		while (StringUtils.contains(changesets, XmlParts.REPLACE_ID)) {
			changesets = replaceId(changesets, startingId);
			startingId++;
		}
		return changesets;
	}

	public static String replaceAuthor(String replaceIn, String author) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_AUTHOR, author);
	}

	public static String replaceId(String replaceIn, int id) {
		return StringUtils.replaceOnce(replaceIn, XmlParts.REPLACE_ID, String.valueOf(id));
	}

	public static String replaceTableName(String replaceIn, String tableName) {
		replaceIn = StringUtils.replace(replaceIn, XmlParts.REPLACE_TABLE_NAME_LOWER_CASE,
				StringUtils.lowerCase(tableName));
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_TABLE_NAME, tableName);
	}

	public static String replaceColumnPrimaryKeyName(String replaceIn, Table table) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_PRIMARY_KEY_NAME,
				table.getPrimaryKeyColumnName());
	}

	public static String replaceConstrainPrimaryKeyName(String replaceIn, Table table) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_CONSTRAIN_PRIMARY_KEY_NAME,
				table.getPrimaryKeyContrainName());
	}

	public static String replaceSequenceName(String replaceIn, Table table) {
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

	public static String replaceColumnForeignKeyName(String replaceIn, AddColumn column) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_NAME,
				column.getForeignKey().getName());
	}

	public static String replaceColumnForeignKeyReferencedTable(String replaceIn, AddColumn column) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME,
				column.getForeignKey().getReferencedTable());
	}

	public static String replaceColumnForeignKeyReferencedColumn(String replaceIn, AddColumn column) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_REFERECED_COLUMN_NAME,
				column.getForeignKey().getReferencedColumn());
	}

	public static String replaceColumnIndexName(String replaceIn, AddColumn column) {
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

	public static String addColumnConstraints(String replaceIn) {
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

}
