package gui.validators;

import gui.components.LBJValueHolderComponent;

/**
 * {@link LBJValueHolderComponent}'s value validator.
 */
public interface LBJValueValidator<T> {

	public boolean isValid(T value);

}
