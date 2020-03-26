package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.Entity;
import domain.ForeignKey;

public class TestSupplier2 extends AbstractEntitiesSupplier {

	private Column createColumnNotification() {
		Column column = new Column("notification", ColumnOperation.ADD_COLUMN);
		column.setDataType("integer");
		column.setForeignKey(new ForeignKey("F_NDER_USER_ID_NDER_ERROR", "NDER_ERROR", "id_nder_error"));
		column.setIndexName("I_NDER_USER_NOTIF");
		column.setIndex(true);
		column.setNullable(false);
		column.setTableName("NDER_NSNCONT_USER");
		return column;
	}

	@Override
	public List<Entity> getEntities() {
		Column column = createColumnNotification();
		return Arrays.asList(column);
	}

}
