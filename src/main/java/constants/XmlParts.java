package constants;

import utils.FileUtils;

public class XmlParts {

	private XmlParts() {
		// only static methods and constants
	}

	// REPLACEMENTS
	public static final String REPLACE_CHAGESETS = "$CHANGESETS$";
	public static final String REPLACE_AUTHOR = "$AUTHOR$";
	public static final String REPLACE_ID = "$ID$";
	public static final String REPLACE_TABLE_NAME = "$TABLE_NAME$";
	public static final String REPLACE_TABLE_NAME_LOWER_CASE = "$TABLE_NAME_LOWER_CASE$";
	public static final String REPLACE_COLUMN_PRIMARY_KEY_NAME = "$COLUMN_PK_NAME$";
	public static final String REPLACE_CONSTRAIN_PRIMARY_KEY_NAME = "$CONSTRAIN_PK_NAME$";
	public static final String REPLACE_SEQUENCE_NAME = "$SEQUENCE_NAME$";
	public static final String REPLACE_COLUMN_NAME = "$COLUMN_NAME$";
	public static final String REPLACE_COLUMN_NAME_UPPER_CASE = "$COLUMN_NAME_UPPER_CASE$";
	public static final String REPLACE_COLUMN_DATA_TYPE = "$COLUMN_TYPE$";
	public static final String REPLACE_COLUMN_FOREIGN_KEY_NAME = "$COLUMN_FK_NAME$";
	public static final String REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME = "$REFERENCED_TABLE_NAME$";
	public static final String REPLACE_COLUMN_FOREIGN_KEY_REFERECED_COLUMN_NAME = "$REFERENCED_COLUMN_NAME$";
	public static final String REPLACE_COLUMN_CONSTRAINS = "$COLUMN_CONSTRAINS$";
	public static final String REPLACE_COLUMN_INDEX_NAME = "$INDEX_NAME$";
	public static final String REPLACE_COLUMN_FOREIGN_KEY = "$COLUMN_FOREIGN_KEY$";
	public static final String REPLACE_COLUMN_NULLABLE = "$NULLABLE$";
	public static final String REPLACE_COLUMN_NULLABLE_VALUE = "$NULLABLE_VALUE$";

	// PATHS TO TEMPLATES
	private static final String CHANGELOG_START_TEMPLATE = "/generator/shared/ChangelogStart.txt";
	private static final String CREATE_TABLE_MSSQL_TEMPLATE = "/generator/createtable/CreateTableMssql.txt";
	private static final String CREATE_TABLE_ORACLE_POSTGRE_TEMPLATE = "/generator/createtable/CreateTableOraclePostgre.txt";
	private static final String CREATE_TABLE_SEQUENCE_POSTGRE_TEMPLATE = "/generator/createtable/CreateTableSequencePostgre.txt";
	private static final String CREATE_TABLE_SEQUENCE_ORACLE_TEMPLATE = "/generator/createtable/CreateTableSequenceOracle.txt";
	private static final String COLUMN_INDEX_MSSQL_POSTGRE_TEMPLATE = "/generator/shared/CreateIndexMssqlPostgre.txt";
	private static final String COLUMN_INDEX_ORACLE_TEMPLATE = "/generator/shared/CreateIndexOracle.txt";
	private static final String ADD_COLUMN_TEMPLATE = "/generator/addcolumn/AddColumn.txt";
	private static final String ADD_COLUMN_BOOLEAN_ORACLE_MSSQL_TEMPLATE = "/generator/addcolumn/AddColumnBooleanOracleMssql.txt";
	private static final String ADD_COLUMN_BOOLEAN_POSTGRE_TEMPLATE = "/generator/addcolumn/AddColumnBooleanPostgre.txt";
	private static final String ADD_COLUMN_CONSTRAINTS_TEMPLATE = "/generator/addcolumn/AddColumnConstraints.txt";
	private static final String ADD_COLUMN_CONSTRAINTS_FOREIGN_KEY_TEMPLATE = "/generator/addcolumn/AddColumnConstraintsForeignKey.txt";
	private static final String ADD_COLUMN_CONSTRAINTS_NULLABLE_TEMPLATE = "/generator/addcolumn/AddColumnConstraintsNullable.txt";
	private static final String REMOVE_NOT_NULL_CONSTRAINT_TEMPLATE = "/generator/removenotnullconstraint/RemoveNotNullConstraint.txt";

	public static String getChangelogStart() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CHANGELOG_START_TEMPLATE);
	}

	public static String getCreateTableMssql() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_MSSQL_TEMPLATE);
	}

	public static String getCreateTableOraclePostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_ORACLE_POSTGRE_TEMPLATE);
	}

	public static String getCreateTableSequenceOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_SEQUENCE_ORACLE_TEMPLATE);
	}

	public static String getCreateTableSequencePostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_SEQUENCE_POSTGRE_TEMPLATE);
	}

	public static String getColumnIndexMssqlPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, COLUMN_INDEX_MSSQL_POSTGRE_TEMPLATE);
	}

	public static String getColumnIndexOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, COLUMN_INDEX_ORACLE_TEMPLATE);
	}

	public static String getAddColumn() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_TEMPLATE);
	}

	public static String getAddColumnBooleanOracleMssql() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_BOOLEAN_ORACLE_MSSQL_TEMPLATE);
	}

	public static String getAddColumnBooleanPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_BOOLEAN_POSTGRE_TEMPLATE);
	}

	public static String getRemoveNotNullConstraint() {
		return FileUtils.getStringFromFileResource(XmlParts.class, REMOVE_NOT_NULL_CONSTRAINT_TEMPLATE);
	}

	public static String getAddColumnConstraints() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_CONSTRAINTS_TEMPLATE);
	}

	public static String getAddColumnConstraintsForeignKey() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_CONSTRAINTS_FOREIGN_KEY_TEMPLATE);
	}

	public static String getAddColumnConstraintsNullable() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_CONSTRAINTS_NULLABLE_TEMPLATE);
	}

}
