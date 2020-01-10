package generator;

import domain.Column;
import domain.ForeignKey;
import domain.Table;

/**
 * Supplies a table with one column that has index and foreign key. Only
 * changesets for column should be generated as {@link #getOperation()} returns
 * {@link Operation#ADD_COLUMN}
 */
public class TestTable1 implements TestTableSupplier {

	private static final String EXPECTED_TABLE = "<databaseChangeLog xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\n"
			+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "	xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog\n"
			+ "                        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.4.xsd\"\n"
			+ "	objectQuotingStrategy=\"QUOTE_ONLY_RESERVED_WORDS\">\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"1\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n"
			+ "				<columnExists tableName=\"NDER_REFERENCE\" columnName=\"nderaction\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column nderaction to NDER_REFERENCE</comment>\n"
			+ "		<addColumn tableName=\"NDER_REFERENCE\">\n"
			+ "			<column name=\"nderaction\" type=\"integer\">\n"
			+ "				<constraints foreignKeyName=\"F_NDER_REF_ID_NDER_ACTION\"\n"
			+ "					referencedTableName=\"NDER_ACTION\" referencedColumnNames=\"id_nder_action\" />\n"
			+ "			</column>\n" + "		</addColumn>\n" + "	</changeSet>\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"2\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<indexExists indexName=\"I_NDER_REFERENCE_NACTION\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Create index I_NDER_REFERENCE_NACTION if it doesn't exist.</comment>\n"
			+ "		<createIndex tableName=\"NDER_REFERENCE\" indexName=\"I_NDER_REFERENCE_NACTION\">\n"
			+ "			<column name=\"nderaction\" />\n" + "		</createIndex>\n" + "	</changeSet>\n"
			+ "</databaseChangeLog>";

	@Override
	public Table getTable() {
		Table table = new Table("NDER_REFERENCE");
		table.addColumn(createColumnNderAction());
		return table;
	}

	private Column createColumnNderAction() {
		Column column = new Column("nderaction");
		column.setDataType("integer");
		column.setForeignKey(new ForeignKey("F_NDER_REF_ID_NDER_ACTION", "NDER_ACTION", "id_nder_action"));
		column.setIndexName("I_NDER_REFERENCE_NACTION");
		column.setIndex(true);
		return column;
	}

	@Override
	public Operation getOperation() {
		return Operation.ADD_COLUMN;
	}

	@Override
	public String getExpectedTable() {
		return EXPECTED_TABLE;
	}

}
