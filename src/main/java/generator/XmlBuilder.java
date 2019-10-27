package generator;

import org.apache.commons.lang3.StringUtils;

import constants.XmlParts;

public class XmlBuilder {

	private XmlBuilder() {
		// only static methods
	}

	/**
	 * Replaces ID of each changeset with incremental number starting from 1 and
	 * puts all of them into changelog.
	 */
	public static String toChangelog(String changesets) {
		int id = 1;
		while (StringUtils.contains(changesets, XmlParts.REPLACE_ID)) {
			changesets = replaceId(changesets, id);
			id++;
		}
		return StringUtils.replace(XmlParts.CHANGELOG, XmlParts.REPLACE_CHAGESETS, changesets);
	}

	public static String replaceAuthor(String replaceIn, String author) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_AUTHOR, author);
	}

	public static String replaceId(String replaceIn, int id) {
		return StringUtils.replaceOnce(replaceIn, XmlParts.REPLACE_ID, String.valueOf(id));
	}

	public static String replaceTableName(String replaceIn, String tableName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_TABLE_NAME, tableName);
	}

	public static String replaceTableNameLowerCase(String replaceIn, String tableName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_TABLE_NAME_LOWER_CASE, StringUtils.lowerCase(tableName));
	}

	public static String replaceColumnPrimaryKeyName(String replaceIn, String primaryKeyName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_PRIMARY_KEY_NAME, primaryKeyName);
	}

	public static String replaceConstrainPrimaryKeyName(String replaceIn, String constrainPrimaryKeyName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_CONSTRAIN_PRIMARY_KEY_NAME, constrainPrimaryKeyName);
	}

	public static String replaceSequenceName(String replaceIn, String sequenceName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_SEQUENCE_NAME, sequenceName);
	}

	public static String replaceColumnName(String replaceIn, String columnName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NAME, columnName);
	}

	public static String replaceColumnNameUpperCase(String replaceIn, String columnName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NAME_UPPER_CASE,
				StringUtils.upperCase(columnName));
	}

	public static String replaceColumnDataType(String replaceIn, String columnDataType) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_DATA_TYPE, columnDataType);
	}

	public static String replaceColumnForeignKeyName(String replaceIn, String foreignKeyName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_NAME, foreignKeyName);
	}

	public static String replaceColumnForeignKeyReferencedTable(String replaceIn, String foreignKeyReferencedTable) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME,
				foreignKeyReferencedTable);
	}

	public static String replaceColumnForeignKeyReferencedColumn(String replaceIn, String foreignKeyReferencedColumn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_REFERECED_COLUMN_NAME,
				foreignKeyReferencedColumn);
	}

	public static String replaceColumnIndexName(String replaceIn, String indexName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_INDEX_NAME, indexName);
	}

	public static String addColumnNullableFalse(String replaceIn) {
		replaceIn = StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NULLABLE, XmlParts.COLUMN_NULLABLE);
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NULLABLE_VALUE, "false");
	}

	public static String addColumnConstraints(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_CONSTRAINS, XmlParts.COLUMN_CONSTRAINS);
	}

	public static String addColumnForeignKeyConstraint(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY,
				XmlParts.COLUMN_FOREIGN_KEY_CONSTRAINT);
	}

	public static String removeColumnForeignKeyConstraint(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY, "");
	}

	public static String removeNullable(String replaceIn) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NULLABLE, "");
	}

	public static String removeConstraints(String addColumn) {
		return StringUtils.replace(addColumn, XmlParts.REPLACE_COLUMN_CONSTRAINS, "");
	}

}
