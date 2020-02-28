package generator;

import domain.Column;
import domain.ForeignKey;
import domain.Table;

/**
 * Supplies a table with one column that has index, foreign key and is NOT
 * nullable. Only changesets for column should be generated as
 * {@link #getOperation()} returns {@link Operation#ADD_COLUMN}
 */
public class TestTable2 extends TestTable implements TestTableSupplier {

	@Override
	public Operation getOperation() {
		return Operation.ADD_COLUMN;
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
