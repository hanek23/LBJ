package domain;

public class Table extends AbstractEntity implements CreateTable, DropTable {

	private String name;
	private String primaryKeyColumnName;
	private String primaryKeyContrainName;
	private String sequenceName;
	private TableOperation operation;

	public Table(String name, TableOperation operation) {
		this.name = name;
		this.operation = operation;
		setForDatabases(ForDatabases.forAll());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public TableOperation getOperation() {
		return operation;
	}

	public void setOperation(TableOperation operation) {
		this.operation = operation;
	}

	@Override
	public boolean isCreateTable() {
		return getOperation() == TableOperation.CREATE;
	}

	@Override
	public boolean isDropTable() {
		return getOperation() == TableOperation.DROP;
	}

}
