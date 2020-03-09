package domain;

import org.apache.commons.lang3.StringUtils;

public class Column {

	private Table table;
	private String name;
	private String indexName;
	private boolean index;
	private ForeignKey foreignKey;
	private boolean nullable;
	private String dataType;

	public Column(String name, String indexName, ForeignKey foreignKey, boolean nullable, String dataType) {
		this.name = name;
		this.index = !StringUtils.isBlank(indexName);
		this.indexName = indexName;
		setForeignKey(foreignKey);
		this.nullable = nullable;
		this.dataType = dataType;
	}

	public Column(String name) {
		nullable = true;
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
		if (foreignKey != null) {
			foreignKey.setColumn(this);
			this.foreignKey = foreignKey;
		}
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public boolean hasForeignKey() {
		return foreignKey != null;
	}

	public boolean hasIndex() {
		return index;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return true if column is NOT nullable or has foreign key to a different
	 *         table
	 */
	public boolean hasConstrains() {
		return !isNullable() || hasForeignKey();
	}

	/**
	 * @return true if and only if type is "boolean" (case is ignored)
	 */
	public boolean isTypeBoolean() {
		return StringUtils.equalsIgnoreCase(dataType, "boolean");
	}

	public boolean isForOracle() {
		return getTable().isForOracle();
	}

	public boolean isForMssql() {
		return getTable().isForMssql();
	}

	public boolean isForPostgreSql() {
		return getTable().isForPostgreSql();
	}

}
