package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.Entity;
import domain.ForeignKey;
import testutils.AbstractXmlSupplier;

public class GeneratorTest2 extends AbstractXmlSupplier implements GeneratorTestCase {

	private Column createColumnNotification() {
		Column column = new Column("notification", ColumnOperation.ADD_COLUMN);
		column.setDataType("integer");
		column.setForeignKey(new ForeignKey("F_LBJ_USER_ID_LBJ_ERROR", "LBJ_ERROR", "id_lbj_error"));
		column.setIndexName("I_LBJ_USER_NOTIF");
		column.setHasIndex(true);
		column.setNullable(false);
		column.setTableName("LBJ_NSNCONT_USER");
		return column;
	}

	@Override
	public List<Entity> getEntities() {
		Column column = createColumnNotification();
		return Arrays.asList(column);
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
