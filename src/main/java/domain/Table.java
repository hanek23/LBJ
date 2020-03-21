package domain;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String name;
	private String primaryKeyColumnName;
	private String primaryKeyContrainName;
	private String sequenceName;
	private List<Column> columns;
	private boolean forOracle;
	private boolean forMssql;
	private boolean forPostgreSql;

	public Table(String name, String primaryKeyColumnName, String primaryKeyContrainName) {
		this.name = name;
		this.primaryKeyColumnName = primaryKeyColumnName;
		this.primaryKeyContrainName = primaryKeyContrainName;
		setForAllDatabases();
	}

	public Table(String name) {
		this.name = name;
		setForAllDatabases();
	}

	/**
	 * @return Table with empty table name, zero columns and for all supported
	 *         databases (Oracle, MSSQL and Postgres).
	 */
	public static Table emptyTable() {
		return new Table("");
	}

	/**
	 * Sets forOracle, forMssql and forPostgre to TRUE
	 */
	public void setForAllDatabases() {
		forOracle = true;
		forMssql = true;
		forPostgreSql = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Column> getColumns() {
		if (columns == null) {
			columns = new ArrayList<>();
		}
		return columns;
	}

	public boolean addColumn(Column column) {
		if (column == null) {
			return false;
		}
		column.setTable(this);
		return getColumns().add(column);
	}

	public void removeColumn(Column toRemove) {
		if (toRemove == null) {
			return;
		}
		getColumns().remove(toRemove);
	}

	public String getPrimaryKeyColumnName() {
		return primaryKeyColumnName;
	}

	public void setPrimaryKeyColumnName(String primaryKeyColumnName) {
		this.primaryKeyColumnName = primaryKeyColumnName;
	}

	public String getPrimaryKeyContrainName() {
		return primaryKeyContrainName;
	}

	public void setPrimaryKeyContrainName(String primaryKeyContrainName) {
		this.primaryKeyContrainName = primaryKeyContrainName;
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

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

}
