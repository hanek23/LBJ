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
	 * @return true if either {@link #hasIndex()} or {@link #hasForeignKey()} return
	 *         true
	 */
	public boolean hasConstrains();
	
	/**
	 * @return true if and only if type is "boolean" (case is ignored)
	 */
	public boolean isTypeBoolean();

}
