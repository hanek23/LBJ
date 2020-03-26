package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.Entity;
import domain.ForeignKey;

public class TestSupplier1 extends AbstractEntitiesSupplier {

	private Column createColumnAction() {
		Column column = new Column("nderaction", ColumnOperation.ADD_COLUMN);
		column.setDataType("integer");
		column.setForeignKey(new ForeignKey("F_NDER_REF_ID_NDER_ACTION", "NDER_ACTION", "id_nder_action"));
		column.setIndexName("I_NDER_REFERENCE_NACTION");
		column.setIndex(true);
		column.setTableName("NDER_REFERENCE");
		return column;
	}

	@Override
	public List<Entity> getEntities() {
		Column column = createColumnAction();
		return Arrays.asList(column);
	}

}
