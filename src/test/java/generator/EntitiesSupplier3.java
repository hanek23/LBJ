package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.Entity;
import domain.DropNotNullConstraint;
import testutils.AbstractXmlSupplier;

public class EntitiesSupplier3 extends AbstractXmlSupplier implements EntitiesSupplier {

	@Override
	public List<Entity> getEntities() {
		DropNotNullConstraint column = new Column("container", ColumnOperation.DROP_NOT_NULL_CONSTRAINT);
		column.setDataType("integer");
		column.setTableName("LBJ_RELATED_ID");
		return Arrays.asList(column);
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
