package domain;

public interface GeneralTable {

	String getName();

	void setName(String name);

	String getSequenceName();

	void setSequenceName(String sequenceName);

	boolean isCreateTable();

	boolean isDropTable();
	
	TableOperation getOperation();

}
