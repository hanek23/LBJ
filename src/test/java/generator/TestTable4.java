package generator;

import domain.Column;
import domain.ForeignKey;
import domain.Table;

/**
 * Supplies a table with eight column. Full changesets for table and all columns
 * should be generated as {@link #getOperation()} returns
 * {@link Operation#CREATE_TABLE}
 */
public class TestTable4 implements TestTableSupplier {

	private static final String EXPECTED_TABLE = "<databaseChangeLog xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\n"
			+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "	xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog\n"
			+ "                        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.4.xsd\"\n"
			+ "	objectQuotingStrategy=\"QUOTE_ONLY_RESERVED_WORDS\">\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"1\" dbms=\"mssql\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<tableExists tableName=\"NDER_RELATED_ID\" />\n"
			+ "			</not>\n" + "		</preConditions>\n" + "\n"
			+ "		<comment>Create table NDER_RELATED_ID</comment>\n"
			+ "		<createTable tableName=\"NDER_RELATED_ID\">\n" + "\n"
			+ "			<column name=\"id_nder_related_id\" type=\"integer\" autoIncrement=\"true\">\n"
			+ "				<constraints primaryKeyName=\"P_NDER_RELATED_ID\" primaryKey=\"true\" />\n"
			+ "			</column>\n" + "		</createTable>\n" + "	</changeSet>\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"2\" dbms=\"oracle, postgresql\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
			+ "				<tableExists tableName=\"NDER_RELATED_ID\" />\n" + "			</not>\n"
			+ "		</preConditions>\n" + "\n" + "		<comment>Create table NDER_RELATED_ID</comment>\n"
			+ "		<createTable tableName=\"NDER_RELATED_ID\">\n" + "\n"
			+ "			<column name=\"id_nder_related_id\" type=\"integer\">\n"
			+ "				<constraints primaryKeyName=\"P_NDER_RELATED_ID\" primaryKey=\"true\" />\n"
			+ "			</column>\n" + "\n" + "		</createTable>\n" + "	</changeSet>\n" + "\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"3\" dbms=\"oracle\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<sequenceExists sequenceName=\"SEQ_NDER_RELATED_ID\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Create sequence SEQ_NDER_RELATED_ID on Oracle if it doesn't exist.</comment>\n"
			+ "		<createSequence sequenceName=\"SEQ_NDER_RELATED_ID\" startValue=\"50\" incrementBy=\"50\"\n"
			+ "			cacheSize=\"20\" maxValue=\"999999999999999999999999999\" ordered=\"false\" cycle=\"false\" />\n"
			+ "	</changeSet>\n" + "\n" + "	<changeSet author=\"phan\" id=\"4\" dbms=\"postgresql\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
			+ "				<sequenceExists sequenceName=\"SEQ_NDER_RELATED_ID\" />\n" + "			</not>\n"
			+ "		</preConditions>\n"
			+ "		<comment>Create sequence SEQ_NDER_RELATED_ID on postgreSQL if it doesn't exist.</comment>\n"
			+ "		<createSequence sequenceName=\"SEQ_NDER_RELATED_ID\" startValue=\"50\" incrementBy=\"50\"\n"
			+ "			cacheSize=\"20\" maxValue=\"999999999999999999\" cycle=\"false\" />\n" + "	</changeSet>\n"
			+ "\n" + "\n" + "	<changeSet author=\"phan\" id=\"5\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n"
			+ "				<columnExists tableName=\"NDER_RELATED_ID\" columnName=\"version_id\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column version_id to NDER_RELATED_ID</comment>\n"
			+ "		<addColumn tableName=\"NDER_RELATED_ID\">\n" + "\n"
			+ "			<column name=\"version_id\" type=\"integer\">\n"
			+ "				<constraints nullable=\"false\" />\n" + "			</column>\n" + "		</addColumn>\n"
			+ "	</changeSet>\n" + "\n" + "\n" + "	<changeSet author=\"phan\" id=\"6\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
			+ "				<columnExists tableName=\"NDER_RELATED_ID\" columnName=\"messageid\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column messageid to NDER_RELATED_ID</comment>\n"
			+ "		<addColumn tableName=\"NDER_RELATED_ID\">\n" + "\n"
			+ "			<column name=\"messageid\" type=\"varchar(50)\">\n" + "			</column>\n"
			+ "		</addColumn>\n" + "	</changeSet>\n" + "\n" + "\n" + "	<changeSet author=\"phan\" id=\"7\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
			+ "				<columnExists tableName=\"NDER_RELATED_ID\" columnName=\"containersn\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column containersn to NDER_RELATED_ID</comment>\n"
			+ "		<addColumn tableName=\"NDER_RELATED_ID\">\n" + "\n"
			+ "			<column name=\"containersn\" type=\"varchar(9)\">\n" + "			</column>\n"
			+ "		</addColumn>\n" + "	</changeSet>\n" + "\n" + "\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"8\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n"
			+ "				<columnExists tableName=\"NDER_RELATED_ID\" columnName=\"actionsn\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column actionsn to NDER_RELATED_ID</comment>\n"
			+ "		<addColumn tableName=\"NDER_RELATED_ID\">\n" + "\n"
			+ "			<column name=\"actionsn\" type=\"varchar(9)\">\n" + "			</column>\n"
			+ "		</addColumn>\n" + "	</changeSet>\n" + "\n" + "\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"9\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n"
			+ "				<columnExists tableName=\"NDER_RELATED_ID\" columnName=\"relatedmessage\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column relatedmessage to NDER_RELATED_ID</comment>\n"
			+ "		<addColumn tableName=\"NDER_RELATED_ID\">\n" + "\n"
			+ "			<column name=\"relatedmessage\" type=\"integer\">\n"
			+ "				<constraints foreignKeyName=\"F_NDER_REL_REL_MESSAGE\"\n"
			+ "					referencedTableName=\"NDER_MESSAGE\" referencedColumnNames=\"id_nder_message\" />\n"
			+ "			</column>\n" + "		</addColumn>\n" + "	</changeSet>\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"10\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<indexExists indexName=\"I_NDER_RELATED_MESSAGE\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Create index I_NDER_RELATED_MESSAGE if it doesn't exist.</comment>\n"
			+ "		<createIndex tableName=\"NDER_RELATED_ID\" indexName=\"I_NDER_RELATED_MESSAGE\">\n"
			+ "			<column name=\"relatedmessage\" />\n" + "		</createIndex>\n" + "	</changeSet>\n" + "\n"
			+ "\n" + "\n" + "	<changeSet author=\"phan\" id=\"11\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
			+ "				<columnExists tableName=\"NDER_RELATED_ID\" columnName=\"relatedcontainer\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column relatedcontainer to NDER_RELATED_ID</comment>\n"
			+ "		<addColumn tableName=\"NDER_RELATED_ID\">\n"
			+ "			<column name=\"relatedcontainer\" type=\"integer\">\n"
			+ "				<constraints foreignKeyName=\"F_NDER_REL_REL_CONTAINER\"\n"
			+ "					referencedTableName=\"NDER_CONTAINER\" referencedColumnNames=\"id_nder_container\" />\n"
			+ "			</column>\n" + "		</addColumn>\n" + "	</changeSet>\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"12\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<indexExists indexName=\"I_NDER_RELATED_CONTAINER\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Create index I_NDER_RELATED_CONTAINER if it doesn't exist.</comment>\n"
			+ "		<createIndex tableName=\"NDER_RELATED_ID\" indexName=\"I_NDER_RELATED_CONTAINER\">\n"
			+ "			<column name=\"relatedcontainer\" />\n" + "		</createIndex>\n" + "	</changeSet>\n" + "\n"
			+ "\n" + "\n" + "\n" + "\n" + "	<changeSet author=\"phan\" id=\"13\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
			+ "				<columnExists tableName=\"NDER_RELATED_ID\" columnName=\"relatedaction\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column relatedaction to NDER_RELATED_ID</comment>\n"
			+ "		<addColumn tableName=\"NDER_RELATED_ID\">\n"
			+ "			<column name=\"relatedaction\" type=\"integer\">\n"
			+ "				<constraints foreignKeyName=\"F_NDER_REL_REL_ACTION\"\n"
			+ "					referencedTableName=\"NDER_ACTION\" referencedColumnNames=\"id_nder_action\" />\n"
			+ "			</column>\n" + "		</addColumn>\n" + "	</changeSet>\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"14\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<indexExists indexName=\"I_NDER_RELATED_ACTION\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Create index I_NDER_RELATED_ACTION if it doesn't exist.</comment>\n"
			+ "		<createIndex tableName=\"NDER_RELATED_ID\" indexName=\"I_NDER_RELATED_ACTION\">\n"
			+ "			<column name=\"relatedaction\" />\n" + "		</createIndex>\n" + "	</changeSet>\n" + "\n"
			+ "\n" + "\n" + "\n" + "\n" + "\n" + "	<changeSet author=\"phan\" id=\"15\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
			+ "				<columnExists tableName=\"NDER_RELATED_ID\" columnName=\"container\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column container to NDER_RELATED_ID</comment>\n"
			+ "		<addColumn tableName=\"NDER_RELATED_ID\">\n"
			+ "			<column name=\"container\" type=\"integer\">\n"
			+ "				<constraints foreignKeyName=\"F_NDER_REL_CONTAINER\"\n"
			+ "					referencedTableName=\"NDER_CONTAINER\" referencedColumnNames=\"id_nder_container\" nullable=\"false\" />\n"
			+ "			</column>\n" + "		</addColumn>\n" + "	</changeSet>\n" + "\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"16\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<indexExists indexName=\"I_NDER_RELATED_ID_CONT\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Create index I_NDER_RELATED_ID_CONT if it doesn't exist.</comment>\n"
			+ "		<createIndex tableName=\"NDER_RELATED_ID\" indexName=\"I_NDER_RELATED_ID_CONT\">\n"
			+ "			<column name=\"container\" />\n" + "		</createIndex>\n" + "	</changeSet>\n" + "\n"
			+ "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "</databaseChangeLog>";

	@Override
	public Operation getOperation() {
		return Operation.CREATE_TABLE;
	}

	@Override
	public String getExpectedTable() {
		return EXPECTED_TABLE;
	}

	@Override
	public Table getTable() {
		Table table = new Table("NDER_RELATED_ID");
		table.setForMssql(true);
		table.setForOracle(true);
		table.setForPostgreSql(true);
		table.setSequenceName("SEQ_NDER_RELATED_ID");
		table.setPrimaryKeyColumnName("id_nder_related_id");
		table.setPrimaryKeyContrainName("P_NDER_RELATED_ID");
		// TODO co to je za pole? index se dělá automaticky?
		table.setPrimaryKeyIndexName("nic?");

		table.addColumn(new Column("version_id", null, null, false, "integer"));

		table.addColumn(new Column("messageid", null, null, true, "varchar(50)"));

		table.addColumn(new Column("containersn", null, null, true, "varchar(9)"));

		table.addColumn(new Column("actionsn", null, null, true, "varchar(9)"));

		table.addColumn(new Column("relatedmessage", "I_NDER_RELATED_MESSAGE",
				new ForeignKey("F_NDER_REL_REL_MESSAGE", "NDER_MESSAGE", "id_nder_message"), true, "integer"));

		table.addColumn(new Column("relatedcontainer", "I_NDER_RELATED_CONTAINER",
				new ForeignKey("F_NDER_REL_REL_CONTAINER", "NDER_CONTAINER", "id_nder_container"), true, "integer"));

		table.addColumn(new Column("relatedaction", "I_NDER_RELATED_ACTION",
				new ForeignKey("F_NDER_REL_REL_ACTION", "NDER_ACTION", "id_nder_action"), true, "integer"));

		table.addColumn(new Column("container", "I_NDER_RELATED_ID_CONT",
				new ForeignKey("F_NDER_REL_CONTAINER", "NDER_CONTAINER", "id_nder_container"), false, "integer"));

		return table;
	}

}
