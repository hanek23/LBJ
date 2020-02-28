package generator;

import domain.Column;
import domain.Table;

/**
 * Supplies a table with two column one of which is boolean. Boolean is
 * different on postgre and oracle/mssql so 3 changeSets are expected. Only
 * changesets for column should be generated as {@link #getOperation()} returns
 * {@link Operation#ADD_COLUMN}
 */
public class TestTable5 extends TestTable implements TestTableSupplier {

	@Override
	public Operation getOperation() {
		return Operation.ADD_COLUMN;
	}

	@Override
	public Table getTable() {
		Table table = new Table("NDER_REFERENCE");
		table.setForAllDatabases();
		table.addColumn(new Column("version_id", null, null, true, "integer"));
		table.addColumn(new Column("isAlive", null, null, false, "boolean"));
		return table;
	}

}
