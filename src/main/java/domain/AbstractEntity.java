package domain;

public abstract class AbstractEntity implements Entity {

	private ForDatabases forDatabases;

	public boolean isForOracle() {
		if (forDatabases == null) {
			return false;
		}
		return forDatabases.isForOracle();
	}

	public void setForOracle(boolean forOracle) {
		if (forDatabases == null) {
			forDatabases = new ForDatabases();
		}
		forDatabases.setForOracle(forOracle);
	}

	public boolean isForMssql() {
		if (forDatabases == null) {
			return false;
		}
		return forDatabases.isForMssql();
	}

	public void setForMssql(boolean forMssql) {
		if (forDatabases == null) {
			forDatabases = new ForDatabases();
		}
		forDatabases.setForMssql(forMssql);
	}

	public boolean isForPostgreSql() {
		if (forDatabases == null) {
			return false;
		}
		return forDatabases.isForPostgreSql();
	}

	public void setForPostgreSql(boolean forPostgreSql) {
		if (forDatabases == null) {
			forDatabases = new ForDatabases();
		}
		forDatabases.setForPostgreSql(forPostgreSql);
	}

	public ForDatabases getForDatabases() {
		return forDatabases;
	}

	public void setForDatabases(ForDatabases forDatabases) {
		this.forDatabases = forDatabases;
	}

}
