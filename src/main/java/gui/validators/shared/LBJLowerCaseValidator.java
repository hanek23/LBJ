package gui.validators.shared;

import org.apache.commons.lang3.StringUtils;

import gui.utils.SimpleBean;
import gui.validators.LBJValueValidator;

/**
 * Validates that components {@link String} value (its letters) are all lower
 * case.
 */
public class LBJLowerCaseValidator implements LBJValueValidator<String>, SimpleBean<LBJLowerCaseValidator> {

	@Override
	public boolean isValid(String value) {
		return StringUtils.isAllLowerCase(value.replaceAll("\\P{L}+", ""));
	}

}
