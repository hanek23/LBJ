package domain;

public interface Entity {

	public boolean isForOracle();

	public void setForOracle(boolean forOracle);

	public boolean isForMssql();

	public void setForMssql(boolean forMssql);

	public boolean isForPostgreSql();

	public void setForPostgreSql(boolean forPostgreSql);

	public ForDatabases getForDatabases();

	public void setForDatabases(ForDatabases forDatabases);

}
