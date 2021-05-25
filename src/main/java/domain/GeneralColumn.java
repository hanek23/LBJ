package domain;

public interface GeneralColumn {

	String getTableName();

	void setTableName(String tableName);

	String getName();

	void setName(String name);

	String getDataType();

	void setDataType(String dataType);

	ColumnOperation getOperation();

	boolean isAddColumn();

	boolean isDropNotNullConstraint();

	boolean isAddNotNullConstraint();

	boolean isDropColumn();

	boolean isModifyDataType();

	boolean isCreateIndex();

	boolean isDropIndex();

	boolean isAddForeignKeyConstraint();

	boolean isDropForeignKeyConstraint();

}
