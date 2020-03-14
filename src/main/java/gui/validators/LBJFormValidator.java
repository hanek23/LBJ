package gui.validators;

import gui.forms.LBJForm;

public interface LBJFormValidator<F extends LBJForm> {

	public boolean isValid(F form);

}
