package gui.validators.shared;

import org.apache.commons.lang3.StringUtils;

import gui.validators.LBJValueValidator;

/**
 * Validates that components {@link String} value is not
 * {@link StringUtils#isBlank(CharSequence)}
 */
public class LBJStringRequiredValidator implements LBJValueValidator<String> {

	@Override
	public boolean isValid(String value) {
		return !StringUtils.isBlank(value);
	}

}
