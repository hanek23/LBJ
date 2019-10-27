package constants;

public class XmlParts {

	private XmlParts() {
		// only static constants
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

	public static final String CHANGELOG = "<databaseChangeLog xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\n"
			+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "	xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog\n"
			+ "                        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.4.xsd\"\n"
			+ "	objectQuotingStrategy=\"QUOTE_ONLY_RESERVED_WORDS\">\n" + "\n" + "	" + REPLACE_CHAGESETS
			+ "	</databaseChangeLog>";

	public static final String CREATE_TABLE_MSSQL = "<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID
			+ "\" dbms=\"mssql\">\n" + "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n"
			+ "			<tableExists tableName=\"" + REPLACE_TABLE_NAME + "\" />\n" + "		</not>\n"
			+ "	</preConditions>\n" + "\n" + "	<comment>Create table " + REPLACE_TABLE_NAME + "</comment>\n"
			+ "	<createTable tableName=\"" + REPLACE_TABLE_NAME + "\">\n" + "\n" + "		<column name=\""
			+ REPLACE_COLUMN_PRIMARY_KEY_NAME + "\" type=\"integer\" autoIncrement=\"true\">\n"
			+ "			<constraints primaryKeyName=\"" + REPLACE_CONSTRAIN_PRIMARY_KEY_NAME
			+ "\" primaryKey=\"true\" />\n" + "		</column>\n" + "\n" + "	</createTable>\n\n";

	public static final String CREATE_TABLE_ORACLE_POSTGRESQL = "</changeSet>\n" + "\n" + "<changeSet author=\""
			+ REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID + "\" dbms=\"oracle, postgresql\">\n"
			+ "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n" + "			<tableExists tableName=\""
			+ REPLACE_TABLE_NAME + "\" />\n" + "		</not>\n" + "	</preConditions>\n" + "\n"
			+ "	<comment>Create table " + REPLACE_TABLE_NAME + "</comment>\n" + "	<createTable tableName=\""
			+ REPLACE_TABLE_NAME + "\">\n" + "\n" + "		<column name=\"" + REPLACE_COLUMN_PRIMARY_KEY_NAME
			+ "\" type=\"integer\">\n" + "			<constraints primaryKeyName=\"" + REPLACE_CONSTRAIN_PRIMARY_KEY_NAME
			+ "\" primaryKey=\"true\" />\n" + "		</column>\n" + "\n" + "	</createTable>\n\n";

	public static final String CREATE_TABLE_SEQUENCE_ORACLE = "</changeSet>\n" + "\n" + "<changeSet author=\""
			+ REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID + "\" dbms=\"oracle\">\n"
			+ "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n" + "			<sequenceExists sequenceName=\""
			+ REPLACE_SEQUENCE_NAME + "\" />\n" + "		</not>\n" + "	</preConditions>\n"
			+ "	<comment>Create sequence " + REPLACE_SEQUENCE_NAME + " on Oracle if it doesn't exist.</comment>\n"
			+ "	<createSequence sequenceName=\"" + REPLACE_SEQUENCE_NAME + "\" startValue=\"50\"\n"
			+ "		incrementBy=\"50\" cacheSize=\"20\" maxValue=\"999999999999999999999999999\" ordered=\"false\"\n"
			+ "		cycle=\"false\" />\n" + "</changeSet>\n\n";

	public static final String CREATE_TABLE_SEQUENCE_POSTGRESQL = "<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\""
			+ REPLACE_ID + "\" dbms=\"postgresql\">\n" + "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n"
			+ "			<sequenceExists sequenceName=\"" + REPLACE_SEQUENCE_NAME + "\" />\n" + "		</not>\n"
			+ "	</preConditions>\n" + "	<comment>Create sequence " + REPLACE_SEQUENCE_NAME
			+ " on postgreSQL if it doesn't exist.</comment>\n" + "	<createSequence sequenceName=\""
			+ REPLACE_SEQUENCE_NAME + "\" startValue=\"50\"\n"
			+ "		incrementBy=\"50\" cacheSize=\"20\" maxValue=\"999999999999999999\" cycle=\"false\" />\n"
			+ "</changeSet>\n\n";

	public static final String COLUMN_INDEX = "<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID
			+ "\">\n" + "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n"
			+ "			<indexExists indexName=\"" + REPLACE_COLUMN_INDEX_NAME + "\" />\n" + "		</not>\n"
			+ "	</preConditions>\n" + "	<comment>Create index " + REPLACE_COLUMN_INDEX_NAME
			+ " if it doesn't exist.</comment>\n" + "	<createIndex tableName=\"" + REPLACE_TABLE_NAME
			+ "\" indexName=\"" + REPLACE_COLUMN_INDEX_NAME + "\">\n" + "		<column name=\"" + REPLACE_COLUMN_NAME
			+ "\" />\n" + "	</createIndex>\n" + "</changeSet>\n\n";

	public static final String ADD_COLUMN = "	<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID
			+ "\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
			+ "				<columnExists tableName=\"" + REPLACE_TABLE_NAME + "\" columnName=\"" + REPLACE_COLUMN_NAME
			+ "\" />\n" + "			</not>\n" + "		</preConditions>\n" + "		<comment>Add column "
			+ REPLACE_COLUMN_NAME + " to " + REPLACE_TABLE_NAME + "</comment>\n" + "		<addColumn tableName=\""
			+ REPLACE_TABLE_NAME + "\">\n" + "			<column name=\"" + REPLACE_COLUMN_NAME + "\" type=\""
			+ REPLACE_COLUMN_DATA_TYPE + "\">\n" + REPLACE_COLUMN_CONSTRAINS + "			</column>\n"
			+ "		</addColumn>\n" + "	</changeSet>\n\n";

	public static final String ADD_COLUMN_BOOLEAN_POSTGRESQL = "	<changeSet id=\"" + REPLACE_ID + "\" author=\""
			+ REPLACE_AUTHOR + "\" dbms=\"postgresql\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<columnExists tableName=\"" + REPLACE_TABLE_NAME
			+ "\" columnName=\"" + REPLACE_COLUMN_NAME + "\" />\n" + "			</not>\n" + "		</preConditions>\n"
			+ "		<addColumn tableName=\"" + REPLACE_TABLE_NAME + "\">\n" + "			<column name=\""
			+ REPLACE_COLUMN_NAME + "\" type=\"boolean\" defaultValue=\"false\" >\n" + REPLACE_COLUMN_CONSTRAINS
			+ "			</column>\n" + "		</addColumn>\n" + "	</changeSet>\n\n";

	public static final String ADD_COLUMN_BOOLEAN_ORACLE_MSSQL = "<changeSet id=\"" + REPLACE_ID + "\" author=\""
			+ REPLACE_AUTHOR + "\"  dbms=\"oracle, mssql\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<columnExists tableName=\"" + REPLACE_TABLE_NAME
			+ "\" columnName=\"" + REPLACE_COLUMN_NAME + "\" />\n" + "			</not>\n" + "		</preConditions>\n"
			+ "		<addColumn tableName=\"" + REPLACE_TABLE_NAME + "\">\n" + "			<column name=\""
			+ REPLACE_COLUMN_NAME + "\" type=\"char(1)\" defaultValue=\"0\">\n" + REPLACE_COLUMN_CONSTRAINS
			+ "			</column> \n" + "		</addColumn>\n" + "	</changeSet>\n\n";

	public static final String COLUMN_CONSTRAINS = "<constraints " + REPLACE_COLUMN_FOREIGN_KEY + " "
			+ REPLACE_COLUMN_NULLABLE + "/>";
	public static final String COLUMN_FOREIGN_KEY_CONSTRAINT = "foreignKeyName=\"" + REPLACE_COLUMN_FOREIGN_KEY_NAME
			+ "\"\n" + "referencedTableName=\"" + REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME + "\" \n"
			+ "referencedColumnNames=\"" + REPLACE_COLUMN_FOREIGN_KEY_REFERECED_COLUMN_NAME + "\"";

	public static final String COLUMN_NULLABLE = "nullable=\"" + REPLACE_COLUMN_NULLABLE_VALUE + "\"";

	public static final String COLUMN_REMOVE = "	<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID
			+ "\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n" + "			<columnExists tableName=\""
			+ REPLACE_TABLE_NAME + "\" columnName=\"" + REPLACE_COLUMN_NAME + "\" />\n" + "		</preConditions>\n"
			+ "		<comment>Remove column " + REPLACE_COLUMN_NAME + " from table " + REPLACE_TABLE_NAME
			+ " if it exists.</comment>\n" + "		<dropColumn columnName=\"" + REPLACE_COLUMN_NAME + "\" tableName=\""
			+ REPLACE_TABLE_NAME + "\" />\n" + "	</changeSet>\n\n";

	public static final String COLUMN_REMOVE_NOT_NULL_CONSTRAINT = "<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\""
			+ REPLACE_ID + "\" dbms=\"oracle\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<sqlCheck expectedResult=\"N\">\n" + "				SELECT NULLABLE\n"
			+ "				FROM USER_TAB_COLUMNS\n" + "				WHERE table_name =\n" + "				'"
			+ REPLACE_TABLE_NAME + "'\n" + "				AND column_name = '" + REPLACE_COLUMN_NAME_UPPER_CASE
			+ "'\n" + "			</sqlCheck>\n" + "		</preConditions>\n"
			+ "		<comment>Remove NOTNULL constraint from column " + REPLACE_COLUMN_NAME_UPPER_CASE + " in table "
			+ REPLACE_TABLE_NAME + " if it exists</comment>\n" + "		<dropNotNullConstraint tableName=\""
			+ REPLACE_TABLE_NAME + "\" columnName=\"" + REPLACE_COLUMN_NAME + "\"\n" + "			columnDataType=\""
			+ REPLACE_COLUMN_DATA_TYPE + "\" />\n" + "	</changeSet>\n" + "\n" + "	<changeSet author=\""
			+ REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID + "\" dbms=\"mssql\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<sqlCheck expectedResult=\"0\">\n"
			+ "				SELECT is_nullable\n" + "				FROM sys.columns\n"
			+ "				WHERE object_id =\n" + "				OBJECT_ID('" + REPLACE_TABLE_NAME + "')\n"
			+ "				AND name = '" + REPLACE_COLUMN_NAME_UPPER_CASE + "'\n" + "			</sqlCheck>\n"
			+ "		</preConditions>\n" + "		<comment>Remove NOTNULL constraint from column "
			+ REPLACE_COLUMN_NAME_UPPER_CASE + " in table " + REPLACE_TABLE_NAME + " if it exists</comment>\n"
			+ "		<dropNotNullConstraint tableName=\"" + REPLACE_TABLE_NAME + "\" columnName=\"" + REPLACE_COLUMN_NAME
			+ "\"\n" + "			columnDataType=\"" + REPLACE_COLUMN_DATA_TYPE + "\" />\n" + "	</changeSet>\n"
			+ "\n" + "	<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID + "\" dbms=\"postgresql\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<sqlCheck expectedResult=\"NO\">\n"
			+ "				SELECT is_nullable\n" + "				FROM information_schema.columns\n"
			+ "				WHERE\n" + "				columns.table_name = '" + REPLACE_TABLE_NAME_LOWER_CASE + "'\n"
			+ "				AND columns.column_name = '" + REPLACE_COLUMN_NAME + "'\n" + "			</sqlCheck>\n"
			+ "		</preConditions>\n" + "		<comment>Remove NOTNULL constraint from column "
			+ REPLACE_COLUMN_NAME_UPPER_CASE + " at table " + REPLACE_TABLE_NAME + " if it exists</comment>\n"
			+ "		<dropNotNullConstraint tableName=\"" + REPLACE_TABLE_NAME + "\" columnName=\"" + REPLACE_COLUMN_NAME
			+ "\"\n" + "			columnDataType=\"" + REPLACE_COLUMN_DATA_TYPE + "\" />\n" + "	</changeSet>\n\n";

}
