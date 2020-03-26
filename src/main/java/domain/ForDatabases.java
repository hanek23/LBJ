package domain;

public class ForDatabases {

	private boolean forOracle;
	private boolean forMssql;
	private boolean forPostgreSql;

	public static ForDatabases forAll() {
		ForDatabases forDatabases = new ForDatabases();
		forDatabases.setForOracle(true);
		forDatabases.setForMssql(true);
		forDatabases.setForPostgreSql(true);
		return forDatabases;
	}

	public boolean isForOracle() {
		return forOracle;
	}

	public void setForOracle(boolean forOracle) {
		this.forOracle = forOracle;
	}

	public boolean isForMssql() {
		return forMssql;
	}

	public void setForMssql(boolean forMssql) {
		this.forMssql = forMssql;
	}

	public boolean isForPostgreSql() {
		return forPostgreSql;
	}

	public void setForPostgreSql(boolean forPostgreSql) {
		this.forPostgreSql = forPostgreSql;
	}

}
