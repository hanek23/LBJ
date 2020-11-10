package domain;

public interface DropColumn extends GeneralColumn, Entity, DropIndex {

	ForeignKey getForeignKey();

	void setForeignKey(ForeignKey foreignKey);

	boolean hasForeignKey();

	boolean hasIndex();

	void setHasIndex(boolean index);

	/**
	 * @return true if either {@link #hasIndex()} or {@link #hasForeignKey()} return
	 *         true
	 */
	boolean hasConstrains();

	boolean hasDefaultValue();

	void setHasDefaultValue(boolean defaultValue);

}
