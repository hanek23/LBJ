package generator;

import domain.Column;
import domain.ForeignKey;
import domain.Table;

/**
 * Supplies a table with one column that has index and foreign key. Only
 * changesets for column should be generated as {@link #getOperation()} returns
 * {@link Operation#ADD_COLUMN}
 */
public class TestTable1 extends TestTable implements TestTableSupplier {

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

}
