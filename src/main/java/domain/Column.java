package domain;

public class Column {

	private Table table;
	private String name;
	private String indexName;
	private ForeignKey foreignKey;
	private boolean nullable;
	private DataType dataType;

	public Column(String name, String indexName, ForeignKey foreignKey, boolean nullable, DataType dataType) {
		this.name = name;
		this.indexName = indexName;
		setForeignKey(foreignKey);
		this.nullable = nullable;
		this.dataType = dataType;
	}

	public Column(String name) {
		this.name = name;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public ForeignKey getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(ForeignKey foreignKey) {
		foreignKey.setColumn(this);
		this.foreignKey = foreignKey;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

}
