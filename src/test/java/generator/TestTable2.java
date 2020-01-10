package generator;

import domain.Column;
import domain.ForeignKey;
import domain.Table;

/**
 * Supplies a table with one column that has index, foreign key and is NOT
 * nullable. Only changesets for column should be generated as
 * {@link #getOperation()} returns {@link Operation#ADD_COLUMN}
 */
public class TestTable2 implements TestTableSupplier {

	private static final String EXPECTED_TABLE = "<databaseChangeLog xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\n"
			+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "	xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog\n"
			+ "                        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.4.xsd\"\n"
			+ "	objectQuotingStrategy=\"QUOTE_ONLY_RESERVED_WORDS\">\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"1\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n"
			+ "				<columnExists tableName=\"NDER_NSNCONT_USER\" columnName=\"notification\"/>\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Add column notification to NDER_NSNCONT_USER</comment>\n"
			+ "		<addColumn tableName=\"NDER_NSNCONT_USER\">\n"
			+ "			<column name=\"notification\" type=\"integer\">\n"
			+ "				<constraints foreignKeyName=\"F_NDER_USER_ID_NDER_ERROR\"\n"
			+ "					referencedTableName=\"NDER_ERROR\" referencedColumnNames=\"id_nder_error\" nullable=\"false\" />\n"
			+ "			</column>\n" + "		</addColumn>\n" + "	</changeSet>\n" + "\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"2\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<not>\n" + "				<indexExists indexName=\"I_NDER_USER_NOTIF\" />\n"
			+ "			</not>\n" + "		</preConditions>\n"
			+ "		<comment>Create index I_NDER_USER_NOTIF if it doesn't exist.</comment>\n"
			+ "		<createIndex tableName=\"NDER_NSNCONT_USER\" indexName=\"I_NDER_USER_NOTIF\">\n"
			+ "			<column name=\"notification\" />\n" + "		</createIndex>\n" + "	</changeSet>\n"
			+ "</databaseChangeLog>";

	@Override
	public Operation getOperation() {
		return Operation.ADD_COLUMN;
	}

	@Override
	public String getExpectedTable() {
		return EXPECTED_TABLE;
	}

	@Override
	public Table getTable() {
		Table table = new Table("NDER_NSNCONT_USER");
		table.addColumn(createColumnNotification());
		return table;
	}

	private Column createColumnNotification() {
		Column column = new Column("notification");
		column.setDataType("integer");
		column.setForeignKey(new ForeignKey("F_NDER_USER_ID_NDER_ERROR", "NDER_ERROR", "id_nder_error"));
		column.setIndexName("I_NDER_USER_NOTIF");
		column.setIndex(true);
		column.setNullable(false);
		return column;
	}

}
