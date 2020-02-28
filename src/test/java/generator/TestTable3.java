package generator;

import domain.Column;
import domain.Table;

/**
 * Supplies a table with one column on which NOT NULL constraint should be
 * removed
 */
public class TestTable3 extends TestTable implements TestTableSupplier {

	@Override
	public Operation getOperation() {
		return Operation.REMOVE_NOT_NULL_CONSTRAINT;
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
