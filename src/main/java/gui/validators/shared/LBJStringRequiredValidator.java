package gui.validators.shared;

import org.apache.commons.lang3.StringUtils;

import gui.utils.Bean;
import gui.utils.SimpleBean;
import gui.validators.LBJValueValidator;

/**
 * Validates that components {@link String} value is not
 * {@link StringUtils#isBlank(CharSequence)}
 */
public class LBJStringRequiredValidator
		implements LBJValueValidator<String>, SimpleBean<LBJStringRequiredValidator> {

	@Override
	public boolean isValid(String value) {
		return !StringUtils.isBlank(value);
	}

}
