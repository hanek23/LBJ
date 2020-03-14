package gui.validators;

import gui.forms.LBJForm;

/**
 * Validator specific to some {@link LBJForm} passed as parameter F.
 * 
 * @param <F> specific form that this validator operates on.
 */
public interface LBJFormValidator<F extends LBJForm> {

	public boolean isValid(F form);

}
