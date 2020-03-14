package gui.validators.shared;

import org.apache.commons.lang3.StringUtils;

import gui.validators.LBJValueValidator;

/**
 * Validates that components {@link String} value (its letters) are all upper
 * case.
 */
public class LBJUpperCaseValidator implements LBJValueValidator<String> {

	@Override
	public boolean isValid(String value) {
		return StringUtils.isAllUpperCase(value.replaceAll("\\P{L}+", ""));
	}

}
