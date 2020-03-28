package domain;

import org.apache.commons.lang3.StringUtils;

public class Column extends AbstractEntity implements AddColumn, RemoveNotNullConstraint {

	private String name;
	private String tableName;
	private boolean isNullable;
	private String dataType;
	private boolean hasIndex;
	private String indexName;
	private ForeignKey foreignKey;
	private ColumnOperation operation;

	public Column(String name, ColumnOperation operation) {
		this.name = name;
		setForDatabases(ForDatabases.forAll());
		this.operation = operation;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	@Override
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	@Override
	public ForeignKey getForeignKey() {
		return foreignKey;
	}

	@Override
	public void setForeignKey(ForeignKey foreignKey) {
		if (foreignKey != null) {
			foreignKey.setColumn(this);
			this.foreignKey = foreignKey;
		}
	}

	@Override
	public boolean isNullable() {
		return isNullable;
	}

	@Override
	public void setNullable(boolean nullable) {
		this.isNullable = nullable;
	}

	@Override
	public boolean hasForeignKey() {
		return foreignKey != null;
	}

	@Override
	public boolean hasIndex() {
		return hasIndex;
	}

	@Override
	public void setIndex(boolean index) {
		this.hasIndex = index;
	}

	@Override
	public String getDataType() {
		return dataType;
	}

	@Override
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public boolean hasConstrains() {
		return !isNullable() || hasForeignKey();
	}

	@Override
	public boolean isTypeBoolean() {
		return StringUtils.equalsIgnoreCase(dataType, "boolean");
	}

	@Override
	public ColumnOperation getOperation() {
		return operation;
	}

	@Override
	public boolean isAddColumn() {
		return getOperation() == ColumnOperation.ADD_COLUMN;
	}

	@Override
	public boolean isRemoveNotNullConstraint() {
		return getOperation() == ColumnOperation.REMOVE_NOT_NULL_CONSTRAINT;
	}

}
