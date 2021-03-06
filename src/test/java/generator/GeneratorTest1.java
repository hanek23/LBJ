package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.Entity;
import domain.ForeignKey;
import testutils.AbstractXmlSupplier;

public class GeneratorTest1 extends AbstractXmlSupplier implements GeneratorTestCase {

	private Column createColumnAction() {
		Column column = new Column("action", ColumnOperation.ADD_COLUMN);
		column.setNullable(true);
		column.setDataType("integer");
		column.setForeignKey(new ForeignKey("F_LBJ_REF_ID_LBJ_ACTION", "LBJ_ACTION", "id_lbj_action"));
		column.setIndexName("I_LBJ_REFERENCE_NACTION");
		column.setHasIndex(true);
		column.setTableName("LBJ_REFERENCE");
		return column;
	}

	@Override
	public List<Entity> getEntities() {
		Column column = createColumnAction();
		return Arrays.asList(column);
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
