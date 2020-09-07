package generator;

import java.util.Arrays;
import java.util.List;

import domain.AddNotNullConstraint;
import domain.Column;
import domain.ColumnOperation;
import domain.Entity;
import testutils.AbstractXmlSupplier;

public class GeneratorTest15 extends AbstractXmlSupplier implements GeneratorTestCase {

	@Override
	public List<Entity> getEntities() {
		AddNotNullConstraint column = new Column("container", ColumnOperation.ADD_NOT_NULL_CONSTRAINT);
		column.setForOracle(false);
		column.setForMssql(true);
		column.setForPostgreSql(false);
		column.setDataType("integer");
		column.setTableName("LBJ_RELATED_ID");
		return Arrays.asList(column);
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
