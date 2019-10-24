package generator;

import org.junit.Test;

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
		System.out.println(Generator.generate(table, Operation.CREATE_TABLE));

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
		return table;
	}

}
