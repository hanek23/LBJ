package domain;

public interface DropColumn extends GeneralColumn, Entity {

	public String getIndexName();

	public void setIndexName(String indexName);

	public ForeignKey getForeignKey();

	public void setForeignKey(ForeignKey foreignKey);

	public boolean hasForeignKey();

	public boolean hasIndex();

	public void setIndex(boolean index);

	/**
	 * @return true if either {@link #hasIndex()} or {@link #hasForeignKey()} return
	 *         true
	 */
	public boolean hasConstrains();

}
