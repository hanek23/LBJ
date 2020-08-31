package gui.validators.shared;

import gui.utils.SimpleBean;
import gui.validators.LBJValueValidator;

/**
 * Third wheel of {@link LBJUpperCaseValidator} and
 * {@link LBJLowerCaseValidator}, does not validate anything
 */
public class LBJNoneCaseValidator implements LBJValueValidator<String>, SimpleBean {

	@Override
	public boolean isValid(String value) {
		return true;
	}

}
