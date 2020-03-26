package domain;

public interface GeneralColumn {

	public String getTableName();

	public void setTableName(String tableName);

	public String getName();

	public void setName(String name);

	public String getDataType();

	public void setDataType(String dataType);

	/**
	 * @return true if and only if type is "boolean" (case is ignored)
	 */
	public boolean isTypeBoolean();

	public ColumnOperation getOperation();

	public boolean isAddColumn();

	public boolean isRemoveNotNullConstraint();

}
