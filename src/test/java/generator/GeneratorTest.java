package generator;

import org.junit.Test;

import domain.Column;
import domain.ForeignKey;
import domain.Table;

public class GeneratorTest {

	public static final String TABLE_NAME = "NDER_CONTAINER";
	public static final String TABLE_PRIMARY_KEY_COLUMN_NAME = "id_nder_container";
	public static final String TABLE_CONSTRAIN_PRIMARY_KEY_NAME = "P_NDER_CONTAINER";
	public static final String TABLE_SEQUENCE_NAME = "SEQ_NDER_CONTAINER";
	public static final String TABLE_PRIMARY_KEY_INDEX_NAME = "I_NDER_CONTAINER";

	@Test
	public void testGenerateTable() {
		Table table = createTable();
		System.out.println(Generator.generate(table, Operation.CREATE_TABLE, "lbj"));

	}

	private Table createTable() {
		Table table = new Table(TABLE_NAME);
		table.setForMssql(true);
		table.setForOracle(true);
		table.setForPostgreSql(true);
		table.setPrimaryKeyColumnName(TABLE_PRIMARY_KEY_COLUMN_NAME);
		table.setPrimaryKeyContrainName(TABLE_CONSTRAIN_PRIMARY_KEY_NAME);
		table.setSequenceName(TABLE_SEQUENCE_NAME);
		table.setPrimaryKeyIndexName(TABLE_PRIMARY_KEY_INDEX_NAME);
		table.addColumn(createVarcharColumn());
		table.addColumn(createBooleanNotNullColumn());
		table.addColumn(createBooleanColumn());
		return table;
	}

	private Column createBooleanNotNullColumn() {
		Column column = new Column("activeNotNull");
		column.setDataType("boolean");
		column.setNullable(false);
		return column;
	}

	private Column createBooleanColumn() {
		Column column = new Column("active");
		column.setDataType("boolean");
		column.setNullable(true);
		return column;
	}

	private Column createVarcharColumn() {
		Column column = new Column("message");
		column.setDataType("varchar(50)");
		column.setIndex(true);
		column.setIndexName("I_NDER_CONTAINER_NDER_MESSAGE");
		column.setNullable(false);

		ForeignKey foreignKey = new ForeignKey("F_NDER_CONTAINER_NDER_MESSAGE");
		foreignKey.setReferencedColumn("container");
		foreignKey.setReferencedTable("NDER_MESSAGE");

		column.setForeignKey(foreignKey);

		return column;
	}

}
