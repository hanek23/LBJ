package domain;

public interface CreateTable extends GeneralTable, Entity {

	String getPrimaryKeyColumnName();

	void setPrimaryKeyColumnName(String primaryKeyColumnName);

	String getPrimaryKeyContrainName();

	void setPrimaryKeyContrainName(String primaryKeyContrainName);


}
