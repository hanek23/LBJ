package domain;

public interface DropNotNullConstraint extends GeneralColumn, Entity {
	
	/**
	 * @return true if and only if type is "boolean" (case is ignored)
	 */
	public boolean isTypeBoolean();

}
