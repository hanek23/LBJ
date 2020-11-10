package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.DropIndex;
import domain.Entity;
import testutils.AbstractXmlSupplier;

public class GeneratorTest18 extends AbstractXmlSupplier implements GeneratorTestCase {

	@Override
	public List<Entity> getEntities() {
		DropIndex drop = new Column(null, ColumnOperation.DROP_INDEX);
		drop.setTableName("LBJ_DROP_INDEX");
		drop.setIndexName("I_LBJ_DROP_INDEX_NOW");

		return Arrays.asList(drop);
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
