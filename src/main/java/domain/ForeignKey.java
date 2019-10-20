package domain;

public class ForeignKey {

	private String name;
	private Column column;
	private String referencedTable;
	private String referencedColumn;

	public ForeignKey(String name) {
		this.name = name;
	}

	public ForeignKey(String name, String referencedTable, String referencedColumn) {
		this.name = name;
		this.referencedTable = referencedTable;
		this.referencedColumn = referencedColumn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public String getReferencedTable() {
		return referencedTable;
	}

	public void setReferencedTable(String referencedTable) {
		this.referencedTable = referencedTable;
	}

	public String getReferencedColumn() {
		return referencedColumn;
	}

	public void setReferencedColumn(String referencedColumn) {
		this.referencedColumn = referencedColumn;
	}

}
