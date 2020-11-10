package domain;

public interface AddColumn extends GeneralColumn, Entity, CreateIndex {

	ForeignKey getForeignKey();

	void setForeignKey(ForeignKey foreignKey);

	boolean isNullable();

	void setNullable(boolean nullable);

	boolean hasForeignKey();

	boolean hasIndex();

	void setHasIndex(boolean index);

	/**
	 * @return true if either {@link #hasIndex()} or {@link #hasForeignKey()} return
	 *         true
	 */
	boolean hasConstrains();

	/**
	 * @return true if and only if type is "boolean" (case is ignored)
	 */
	boolean isTypeBoolean();

	String getDefaultValue();

	void setDefaultValue(String defaultValue);

}
