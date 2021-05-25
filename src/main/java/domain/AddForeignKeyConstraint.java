package domain;

public interface AddForeignKeyConstraint extends GeneralColumn, Entity {

	ForeignKey getForeignKey();

	void setForeignKey(ForeignKey foreignKey);

}
