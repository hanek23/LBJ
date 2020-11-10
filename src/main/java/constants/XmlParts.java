package constants;

import generator.Generator;
import utils.FileUtils;

/**
 * Static access to XML parts for generating final outcome of this application
 * using {@link Generator}.
 */
public class XmlParts {

	private XmlParts() {
		// only static methods and constants
	}

	// Replacements
	private static final String XML_REPLACE_START = "${";
	private static final String XML_REPLACE_END = "}$";
	protected static final String REPLACE_CHAGESETS = XML_REPLACE_START + "CHANGESETS" + XML_REPLACE_END;
	protected static final String REPLACE_AUTHOR = XML_REPLACE_START + "AUTHOR" + XML_REPLACE_END;
	protected static final String REPLACE_ID = XML_REPLACE_START + "ID" + XML_REPLACE_END;
	protected static final String REPLACE_TABLE_NAME = XML_REPLACE_START + "TABLE_NAME" + XML_REPLACE_END;
	protected static final String REPLACE_TABLE_NAME_LOWER_CASE = XML_REPLACE_START + "TABLE_NAME_LOWER_CASE"
			+ XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_PRIMARY_KEY_NAME = XML_REPLACE_START + "COLUMN_PK_NAME"
			+ XML_REPLACE_END;
	protected static final String REPLACE_CONSTRAIN_PRIMARY_KEY_NAME = XML_REPLACE_START + "CONSTRAIN_PK_NAME"
			+ XML_REPLACE_END;
	protected static final String REPLACE_SEQUENCE_NAME = XML_REPLACE_START + "SEQUENCE_NAME" + XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_NAME = XML_REPLACE_START + "COLUMN_NAME" + XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_NAME_UPPER_CASE = XML_REPLACE_START + "COLUMN_NAME_UPPER_CASE"
			+ XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_DATA_TYPE = XML_REPLACE_START + "COLUMN_TYPE" + XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_FOREIGN_KEY_NAME = XML_REPLACE_START + "COLUMN_FK_NAME"
			+ XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME = XML_REPLACE_START
			+ "REFERENCED_TABLE_NAME" + XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_FOREIGN_KEY_REFERECED_COLUMN_NAME = XML_REPLACE_START
			+ "REFERENCED_COLUMN_NAME" + XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_CONSTRAINS = XML_REPLACE_START + "COLUMN_CONSTRAINS" + XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_INDEX_NAME = XML_REPLACE_START + "INDEX_NAME" + XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_FOREIGN_KEY = XML_REPLACE_START + "COLUMN_FOREIGN_KEY"
			+ XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_NULLABLE = XML_REPLACE_START + "NULLABLE" + XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_NULLABLE_VALUE = XML_REPLACE_START + "NULLABLE_VALUE"
			+ XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_DEFAULT_VALUE_BASE = XML_REPLACE_START + "COLUMN_DEFAULT_VALUE_BASE"
			+ XML_REPLACE_END;
	protected static final String REPLACE_COLUMN_DEFAULT_VALUE = XML_REPLACE_START + "COLUMN_DEFAULT_VALUE"
			+ XML_REPLACE_END;
	protected static final String REPLACE_DROP_COLUMN_DEFAULT_VALUE = XML_REPLACE_START + "DROP_COLUMN_DEFAULT_VALUE"
			+ XML_REPLACE_END;
	protected static final String REPLACE_LBJ_VERSION = XML_REPLACE_START + "LBJ_VERSION" + XML_REPLACE_END;

	// Paths to templates
	private static final String CHANGELOG_START_TEMPLATE = "/generator/shared/ChangeLogStart.txt";
	private static final String CHANGESETS_START_TEMPLATE = "/generator/shared/ChangeSetsStart.txt";

	private static final String CREATE_TABLE_MSSQL_TEMPLATE = "/generator/createtable/CreateTableMssql.txt";
	private static final String CREATE_TABLE_ORACLE_POSTGRE_TEMPLATE = "/generator/createtable/CreateTableOraclePostgre.txt";
	private static final String CREATE_TABLE_SEQUENCE_POSTGRE_TEMPLATE = "/generator/createtable/CreateTableSequencePostgre.txt";
	private static final String CREATE_TABLE_SEQUENCE_ORACLE_TEMPLATE = "/generator/createtable/CreateTableSequenceOracle.txt";

	private static final String ADD_COLUMN_TEMPLATE = "/generator/addcolumn/AddColumn.txt";
	private static final String ADD_COLUMN_BOOLEAN_ORACLE_MSSQL_TEMPLATE = "/generator/addcolumn/AddColumnBooleanOracleMssql.txt";
	private static final String ADD_COLUMN_BOOLEAN_POSTGRE_TEMPLATE = "/generator/addcolumn/AddColumnBooleanPostgre.txt";
	private static final String ADD_COLUMN_CONSTRAINTS_TEMPLATE = "/generator/addcolumn/AddColumnConstraints.txt";
	private static final String ADD_COLUMN_CONSTRAINTS_FOREIGN_KEY_TEMPLATE = "/generator/addcolumn/AddColumnConstraintsForeignKey.txt";
	private static final String ADD_COLUMN_CONSTRAINTS_NULLABLE_TEMPLATE = "/generator/addcolumn/AddColumnConstraintsNullable.txt";
	private static final String ADD_COLUMN_DEFAULT_VALUE_TEMPLATE = "/generator/addcolumn/AddColumnDefaultValue.txt";

	private static final String DROP_COLUMN_TEMPLATE = "/generator/dropcolumn/DropColumn.txt";
	private static final String DROP_FOREIGN_KEY_TEMPLATE = "/generator/dropcolumn/DropForeignKey.txt";
	private static final String DROP_COLUMN_DEFAULT_VALUE_TEMPLATE = "/generator/dropcolumn/DropDefaultValue.txt";

	private static final String DROP_NOT_NULL_CONSTRAINT_ORACLE_TEMPLATE = "/generator/dropnotnullconstraint/DropNotNullConstraintOracle.txt";
	private static final String DROP_NOT_NULL_CONSTRAINT_MSSQL_TEMPLATE = "/generator/dropnotnullconstraint/DropNotNullConstraintMssql.txt";
	private static final String DROP_NOT_NULL_CONSTRAINT_POSTGRE_TEMPLATE = "/generator/dropnotnullconstraint/DropNotNullConstraintPostgre.txt";

	private static final String ADD_NOT_NULL_CONSTRAINT_ORACLE_TEMPLATE = "/generator/addnotnullconstraint/AddNotNullConstraintOracle.txt";
	private static final String ADD_NOT_NULL_CONSTRAINT_MSSQL_TEMPLATE = "/generator/addnotnullconstraint/AddNotNullConstraintMssql.txt";
	private static final String ADD_NOT_NULL_CONSTRAINT_POSTGRE_TEMPLATE = "/generator/addnotnullconstraint/AddNotNullConstraintPostgre.txt";

	private static final String DROP_TABLE_SEQUENCE_TEMPLATE = "/generator/droptable/DropSequence.txt";
	private static final String DROP_TABLE_TEMPLATE = "/generator/droptable/DropTable.txt";

	private static final String MODIFY_DATA_TYPE_TEMPLATE = "/generator/modifydatatype/ModifyDataType.txt";
	
	private static final String CREATE_INDEX_MSSQL_POSTGRE_TEMPLATE = "/generator/createindex/CreateIndexMssqlPostgre.txt";
	private static final String CREATE_INDEX_ORACLE_TEMPLATE = "/generator/createindex/CreateIndexOracle.txt";
	
	private static final String DROP_INDEX_MSSQL_POSTGRE_TEMPLATE = "/generator/dropindex/DropIndexMssqlPostgre.txt";
	private static final String DROP_INDEX_ORACLE_TEMPLATE = "/generator/dropindex/DropIndexOracle.txt";

	protected static String getChangelogStart() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CHANGELOG_START_TEMPLATE);
	}

	protected static String getChangesetsStart() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CHANGESETS_START_TEMPLATE);
	}

	protected static String getCreateTableMssql() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_MSSQL_TEMPLATE);
	}

	protected static String getCreateTableOraclePostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_ORACLE_POSTGRE_TEMPLATE);
	}

	protected static String getCreateTableSequenceOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_SEQUENCE_ORACLE_TEMPLATE);
	}

	protected static String getCreateTableSequencePostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_SEQUENCE_POSTGRE_TEMPLATE);
	}

	protected static String getColumnIndexMssqlPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_INDEX_MSSQL_POSTGRE_TEMPLATE);
	}

	protected static String getColumnIndexOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_INDEX_ORACLE_TEMPLATE);
	}

	protected static String getAddGeneralColumnBase() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_TEMPLATE);
	}

	protected static String getAddColumnDefaultValue() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_DEFAULT_VALUE_TEMPLATE);
	}

	protected static String getDropColumnBase() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_COLUMN_TEMPLATE);
	}

	protected static String getDropIndexMssqlPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_INDEX_MSSQL_POSTGRE_TEMPLATE);
	}

	protected static String getDropIndexOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_INDEX_ORACLE_TEMPLATE);
	}

	protected static String getDropForeignKey() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_FOREIGN_KEY_TEMPLATE);
	}

	protected static String getBooleanCollumnOracleMssql() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_BOOLEAN_ORACLE_MSSQL_TEMPLATE);
	}

	protected static String getBooleanColumnPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_BOOLEAN_POSTGRE_TEMPLATE);
	}

	protected static String getDropNotNullConstraintOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_NOT_NULL_CONSTRAINT_ORACLE_TEMPLATE);
	}

	protected static String getDropNotNullConstraintMssql() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_NOT_NULL_CONSTRAINT_MSSQL_TEMPLATE);
	}

	protected static String getDropNotNullConstraintPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_NOT_NULL_CONSTRAINT_POSTGRE_TEMPLATE);
	}

	protected static String getAddNotNullConstraintOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_NOT_NULL_CONSTRAINT_ORACLE_TEMPLATE);
	}

	protected static String getAddNotNullConstraintMssql() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_NOT_NULL_CONSTRAINT_MSSQL_TEMPLATE);
	}

	protected static String getAddNotNullConstraintPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_NOT_NULL_CONSTRAINT_POSTGRE_TEMPLATE);
	}

	protected static String getAddColumnConstraints() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_CONSTRAINTS_TEMPLATE);
	}

	protected static String getAddColumnConstraintsForeignKey() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_CONSTRAINTS_FOREIGN_KEY_TEMPLATE);
	}

	protected static String getAddColumnConstraintsNullable() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_CONSTRAINTS_NULLABLE_TEMPLATE);
	}

	protected static String getDropSequenceBase() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_TABLE_SEQUENCE_TEMPLATE);
	}

	protected static String getDropTableBase() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_TABLE_TEMPLATE);
	}

	protected static String getModifyDataTypeBase() {
		return FileUtils.getStringFromFileResource(XmlParts.class, MODIFY_DATA_TYPE_TEMPLATE);
	}

	public static String getDropColumnDefaultValue() {
		return FileUtils.getStringFromFileResource(XmlParts.class, DROP_COLUMN_DEFAULT_VALUE_TEMPLATE);
	}

}
