package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.CreateIndex;
import domain.Entity;
import testutils.AbstractXmlSupplier;

public class GeneratorTest19 extends AbstractXmlSupplier implements GeneratorTestCase {

	@Override
	public List<Entity> getEntities() {
		CreateIndex forMssqlPostgre = new Column(null, ColumnOperation.DROP_INDEX);
		forMssqlPostgre.setForOracle(false);
		forMssqlPostgre.setForMssql(true);
		forMssqlPostgre.setForPostgreSql(true);
		forMssqlPostgre.setTableName("LBJ_DROP_INDEX");
		forMssqlPostgre.setIndexName("I_LBJ_DROP_INDEX_MSSQL_POSTGRE");

		CreateIndex forOracle = new Column(null, ColumnOperation.DROP_INDEX);
		forOracle.setForOracle(true);
		forOracle.setForMssql(false);
		forOracle.setForPostgreSql(false);
		forOracle.setTableName("LBJ_DROP_INDEX");
		forOracle.setIndexName("I_LBJ_DROP_INDEX_ORACLE");

		return Arrays.asList(forMssqlPostgre, forOracle);
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
