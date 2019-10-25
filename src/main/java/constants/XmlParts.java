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
	public static final String REPLACE_COLUMN_PRIMARY_KEY_NAME = "$COLUMN_PK_NAME$";
	public static final String REPLACE_CONSTRAIN_PRIMARY_KEY_NAME = "$CONSTRAIN_PK_NAME$";
	public static final String REPLACE_SEQUENCE_NAME = "$SEQUENCE_NAME$";
	public static final String REPLACE_COLUMN_NAME = "$COLUMN_NAME$";
	public static final String REPLACE_COLUMN_DATA_TYPE = "$COLUMN_TYPE$";
	public static final String REPLACE_COLUMN_FOREIGN_KEY_NAME = "$COLUMN_FK_NAME$";
	public static final String REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME = "$REFERENCED_TABLE_NAME$";
	public static final String REPLACE_COLUMN_FOREIGN_KEY_REFERECED_COLUMN_NAME = "$REFERENCED_COLUMN_NAME$";
	public static final String REPLACE_COLUMN_CONSTRAINS = "$COLUMN_CONSTRAINS";
	public static final String REPLACE_COLUMN_INDEX_NAME = "$INDEX_NAME$";
	public static final String REPLACE_COLUMN_FOREIGN_KEY = "$COLUMN_FOREIGN_KEY$";
	public static final String REPLACE_COLUMN_NULLABLE = "$NULLABLE$";
	public static final String REPLACE_COLUMN_NULLABLE_VALUE = "$NULLABLE_VALUE$";

	public static final String CHANGELOG = "<databaseChangeLog xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\n"
			+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "	xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog\n"
			+ "                        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.4.xsd\"\n"
			+ "	objectQuotingStrategy=\"QUOTE_ONLY_RESERVED_WORDS\">\n" + "\n" + "	" + REPLACE_CHAGESETS + "\n" + "\n"
			+ "	</databaseChangeLog>";

	public static final String CREATE_TABLE_MSSQL = "<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID
			+ "\" dbms=\"mssql\">\n" + "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n"
			+ "			<tableExists tableName=\"" + REPLACE_TABLE_NAME + "\" />\n" + "		</not>\n"
			+ "	</preConditions>\n" + "\n" + "	<comment>Create table " + REPLACE_TABLE_NAME + "</comment>\n"
			+ "	<createTable tableName=\"" + REPLACE_TABLE_NAME + "\">\n" + "\n" + "		<column name=\""
			+ REPLACE_COLUMN_PRIMARY_KEY_NAME + "\" type=\"integer\" autoIncrement=\"true\">\n"
			+ "			<constraints primaryKeyName=\"" + REPLACE_CONSTRAIN_PRIMARY_KEY_NAME
			+ "\" primaryKey=\"true\" />\n" + "		</column>\n" + "\n" + "	</createTable>\n";

	public static final String CREATE_TABLE_ORACLE_POSTGRESQL = "</changeSet>\n" + "\n" + "<changeSet author=\""
			+ REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID + "\" dbms=\"oracle, postgresql\">\n"
			+ "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n" + "			<tableExists tableName=\""
			+ REPLACE_TABLE_NAME + "\" />\n" + "		</not>\n" + "	</preConditions>\n" + "\n"
			+ "	<comment>Create table " + REPLACE_TABLE_NAME + "</comment>\n" + "	<createTable tableName=\""
			+ REPLACE_TABLE_NAME + "\">\n" + "\n" + "		<column name=\"" + REPLACE_COLUMN_PRIMARY_KEY_NAME
			+ "\" type=\"integer\">\n" + "			<constraints primaryKeyName=\"" + REPLACE_CONSTRAIN_PRIMARY_KEY_NAME
			+ "\" primaryKey=\"true\" />\n" + "		</column>\n" + "\n" + "	</createTable>\n";

	public static final String CREATE_TABLE_SEQUENCE_ORACLE = "</changeSet>\n" + "\n" + "<changeSet author=\""
			+ REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID + "\" dbms=\"oracle\">\n"
			+ "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n" + "			<sequenceExists sequenceName=\""
			+ REPLACE_SEQUENCE_NAME + "\" />\n" + "		</not>\n" + "	</preConditions>\n"
			+ "	<comment>Create sequence " + REPLACE_SEQUENCE_NAME + " on Oracle if it doesn't exist.</comment>\n"
			+ "	<createSequence sequenceName=\"" + REPLACE_SEQUENCE_NAME + "\" startValue=\"50\"\n"
			+ "		incrementBy=\"50\" cacheSize=\"20\" maxValue=\"999999999999999999999999999\" ordered=\"false\"\n"
			+ "		cycle=\"false\" />\n" + "</changeSet>\n";

	public static final String CREATE_TABLE_SEQUENCE_POSTGRESQL = "<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\""
			+ REPLACE_ID + "\" dbms=\"postgresql\">\n" + "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n"
			+ "			<sequenceExists sequenceName=\"" + REPLACE_SEQUENCE_NAME + "\" />\n" + "		</not>\n"
			+ "	</preConditions>\n" + "	<comment>Create sequence " + REPLACE_SEQUENCE_NAME
			+ " on postgreSQL if it doesn't exist.</comment>\n" + "	<createSequence sequenceName=\""
			+ REPLACE_SEQUENCE_NAME + "\" startValue=\"50\"\n"
			+ "		incrementBy=\"50\" cacheSize=\"20\" maxValue=\"999999999999999999\" cycle=\"false\" />\n"
			+ "</changeSet>\n";

	public static final String COLUMN_INDEX = "<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID
			+ "\">\n" + "	<preConditions onFail=\"MARK_RAN\">\n" + "		<not>\n"
			+ "			<indexExists indexName=\"" + REPLACE_COLUMN_INDEX_NAME + "\" />\n" + "		</not>\n"
			+ "	</preConditions>\n" + "	<comment>Create index " + REPLACE_COLUMN_INDEX_NAME
			+ " if it doesn't exist.</comment>\n" + "	<createIndex tableName=\"" + REPLACE_TABLE_NAME
			+ "\" indexName=\"" + REPLACE_COLUMN_INDEX_NAME + "\">\n" + "		<column name=\"" + REPLACE_COLUMN_NAME
			+ "\" />\n" + "	</createIndex>\n" + "</changeSet>";

	public static final String ADD_COLUMN = "	<changeSet author=\"" + REPLACE_AUTHOR + "\" id=\"" + REPLACE_ID
			+ "\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
			+ "				<columnExists tableName=\"" + REPLACE_TABLE_NAME + "\" columnName=\"" + REPLACE_COLUMN_NAME
			+ "\" />\n" + "			</not>\n" + "		</preConditions>\n" + "		<comment>Add column "
			+ REPLACE_COLUMN_NAME + " to " + REPLACE_TABLE_NAME + "</comment>\n" + "		<addColumn tableName=\""
			+ REPLACE_TABLE_NAME + "\">\n" + "			<column name=\"" + REPLACE_COLUMN_NAME + "\" type=\""
			+ REPLACE_COLUMN_DATA_TYPE + "\">\n" + REPLACE_COLUMN_CONSTRAINS + "			</column>\n"
			+ "		</addColumn>\n" + "	</changeSet>";

	public static final String COLUMN_CONSTRAINS = "<constraints " + REPLACE_COLUMN_FOREIGN_KEY + " "
			+ REPLACE_COLUMN_NULLABLE + "/>";
	public static final String COLUMN_FOREIGN_KEY_CONSTRAINT = "foreignKeyName=\"" + REPLACE_COLUMN_FOREIGN_KEY_NAME
			+ "\"\n" + "referencedTableName=\"" + REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME + "\" \n"
			+ "referencedColumnNames=\"" + REPLACE_COLUMN_FOREIGN_KEY_REFERECED_COLUMN_NAME + "\"";

	public static final String COLUMN_NULLABLE = "nullable=\"" + REPLACE_COLUMN_NULLABLE_VALUE + "\"";

}
