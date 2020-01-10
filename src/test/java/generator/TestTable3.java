package generator;

import domain.Column;
import domain.Table;

/**
 * Supplies a table with one column on which NOT NULL constraint should be
 * removed
 */
public class TestTable3 implements TestTableSupplier {

	private static final String EXPECTED_TABLE = "<databaseChangeLog xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\n"
			+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "	xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog\n"
			+ "                        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.4.xsd\"\n"
			+ "	objectQuotingStrategy=\"QUOTE_ONLY_RESERVED_WORDS\">\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"1\" dbms=\"oracle\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<sqlCheck expectedResult=\"N\">\n" + "				SELECT NULLABLE\n"
			+ "				FROM USER_TAB_COLUMNS\n" + "				WHERE table_name =\n"
			+ "				'NDER_RELATED_ID'\n" + "				AND column_name = 'CONTAINER'\n"
			+ "			</sqlCheck>\n" + "		</preConditions>\n"
			+ "		<comment>Remove NOTNULL constraint from column CONTAINER in table NDER_RELATED_ID if it exists</comment>\n"
			+ "		<dropNotNullConstraint tableName=\"NDER_RELATED_ID\" columnName=\"container\"\n"
			+ "			columnDataType=\"integer\" />\n" + "	</changeSet>\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"2\" dbms=\"mssql\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
			+ "			<sqlCheck expectedResult=\"0\">\n" + "				SELECT is_nullable\n"
			+ "				FROM sys.columns\n" + "				WHERE object_id =\n"
			+ "				OBJECT_ID('NDER_RELATED_ID')\n" + "				AND name = 'CONTAINER'\n"
			+ "			</sqlCheck>\n" + "		</preConditions>\n"
			+ "		<comment>Remove NOTNULL constraint from column CONTAINER in table NDER_RELATED_ID if it exists</comment>\n"
			+ "		<dropNotNullConstraint tableName=\"NDER_RELATED_ID\" columnName=\"container\"\n"
			+ "			columnDataType=\"integer\" />\n" + "	</changeSet>\n" + "\n"
			+ "	<changeSet author=\"phan\" id=\"3\" dbms=\"postgresql\">\n"
			+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<sqlCheck expectedResult=\"NO\">\n"
			+ "				SELECT is_nullable\n" + "				FROM information_schema.columns\n"
			+ "				WHERE\n" + "				columns.table_name = 'nder_related_id'\n"
			+ "				AND columns.column_name = 'container'\n" + "			</sqlCheck>\n"
			+ "		</preConditions>\n"
			+ "		<comment>Remove NOTNULL constraint from column container in table NDER_RELATED_ID if it exists</comment>\n"
			+ "		<dropNotNullConstraint tableName=\"NDER_RELATED_ID\" columnName=\"container\"\n"
			+ "			columnDataType=\"integer\" />\n" + "	</changeSet>\n" + "\n" + "</databaseChangeLog>";

	@Override
	public Operation getOperation() {
		return Operation.REMOVE_NOT_NULL_CONSTRAINT;
	}

	@Override
	public String getExpectedTable() {
		return EXPECTED_TABLE;
	}

	@Override
	public Table getTable() {
		Table table = new Table("NDER_RELATED_ID");
		Column column = new Column("container");
		column.setDataType("integer");
		table.addColumn(column);
		return table;
	}

}
