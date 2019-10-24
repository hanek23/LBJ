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

	public static String replaceColumnType(String replaceIn, String columnType) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_TYPE, columnType);
	}

	public static String replaceColumnForeignKeyName(String replaceIn, String foreignKeyName) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME, foreignKeyName);
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

	public static String replaceColumnNullable(String replaceIn, String nullable) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NULLABLE, nullable);
	}

	public static String replaceColumnNullableValue(String replaceIn, boolean nullableValue) {
		return StringUtils.replace(replaceIn, XmlParts.REPLACE_COLUMN_NULLABLE_VALUE, String.valueOf(nullableValue));
	}

}
