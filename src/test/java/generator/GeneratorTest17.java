package generator;

import java.util.Arrays;
import java.util.List;

import domain.Column;
import domain.ColumnOperation;
import domain.CreateIndex;
import domain.Entity;
import testutils.AbstractXmlSupplier;

public class GeneratorTest17 extends AbstractXmlSupplier implements GeneratorTestCase {

	@Override
	public List<Entity> getEntities() {
		CreateIndex forMssqlPostgre = new Column("mssqlpostgre", ColumnOperation.CREATE_INDEX);
		forMssqlPostgre.setForOracle(false);
		forMssqlPostgre.setForMssql(true);
		forMssqlPostgre.setForPostgreSql(true);
		forMssqlPostgre.setTableName("LBJ_TWO");
		forMssqlPostgre.setIndexName("I_LBJ_TWO_MSSQLPOSTGRE");

		CreateIndex forOracle = new Column("oracle", ColumnOperation.CREATE_INDEX);
		forOracle.setForOracle(true);
		forOracle.setForMssql(false);
		forOracle.setForPostgreSql(false);
		forOracle.setTableName("LBJ_ONE");
		forOracle.setIndexName("I_LBJ_ONE_ORACLE");

		CreateIndex forAll = new Column("all", ColumnOperation.CREATE_INDEX);
		forAll.setForOracle(true);
		forAll.setForMssql(true);
		forAll.setForPostgreSql(true);
		forAll.setTableName("LBJ");
		forAll.setIndexName("I_LBJ_ALL");

		return Arrays.asList(forMssqlPostgre, forOracle, forAll);
	}

	@Override
	public boolean checkXsd() {
		return true;
	}

}
