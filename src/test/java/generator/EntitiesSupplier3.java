package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.Entity;
import domain.RemoveNotNullConstraint;

public class EntitiesSupplier3 extends AbstractEntitiesSupplier {

	@Override
	public List<Entity> getEntities() {
		RemoveNotNullConstraint column = new Column("container", ColumnOperation.REMOVE_NOT_NULL_CONSTRAINT);
		column.setDataType("integer");
		column.setTableName("LBJ_RELATED_ID");
		return Arrays.asList(column);
	}

}
