package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.Entity;
import domain.ForeignKey;

public class EntitiesSupplier1 extends AbstractEntitiesSupplier {

	private Column createColumnAction() {
		Column column = new Column("action", ColumnOperation.ADD_COLUMN);
		column.setDataType("integer");
		column.setForeignKey(new ForeignKey("F_LBJ_REF_ID_LBJ_ACTION", "LBJ_ACTION", "id_lbj_action"));
		column.setIndexName("I_LBJ_REFERENCE_NACTION");
		column.setIndex(true);
		column.setTableName("LBJ_REFERENCE");
		return column;
	}

	@Override
	public List<Entity> getEntities() {
		Column column = createColumnAction();
		return Arrays.asList(column);
	}

}
