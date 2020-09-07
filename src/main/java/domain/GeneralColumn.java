package domain;

public interface GeneralColumn {

	public String getTableName();

	public void setTableName(String tableName);

	public String getName();

	public void setName(String name);

	public String getDataType();

	public void setDataType(String dataType);

	public ColumnOperation getOperation();

	public boolean isAddColumn();

	public boolean isDropNotNullConstraint();
	
	public boolean isAddNotNullConstraint();

	public boolean isDropColumn();

	public boolean isModifyDataType();

}
