package gui.validators;

import gui.components.LBJValueComponent;

/**
 * {@link LBJValueComponent}'s value validator.
 */
public interface LBJValueValidator<T> {

	public boolean isValid(T value);

}
