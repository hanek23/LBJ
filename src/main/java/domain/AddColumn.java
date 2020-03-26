package domain;

public interface AddColumn extends GeneralColumn, Entity {

	public String getIndexName();

	public void setIndexName(String indexName);

	public ForeignKey getForeignKey();

	public void setForeignKey(ForeignKey foreignKey);

	public boolean isNullable();

	public void setNullable(boolean nullable);

	public boolean hasForeignKey();

	public boolean hasIndex();

	public void setIndex(boolean index);

	/**
	 * @return true if column is NOT nullable or has foreign key to a different
	 *         table
	 */
	public boolean hasConstrains();

}
