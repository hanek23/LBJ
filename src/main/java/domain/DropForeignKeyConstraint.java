package domain;

public interface DropForeignKeyConstraint extends GeneralColumn, Entity {

	ForeignKey getForeignKey();

	void setForeignKey(ForeignKey foreignKey);

}
