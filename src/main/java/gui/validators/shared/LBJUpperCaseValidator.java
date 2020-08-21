package gui.validators.shared;

import org.apache.commons.lang3.StringUtils;

import gui.utils.Bean;
import gui.utils.SimpleBean;
import gui.validators.LBJValueValidator;

/**
 * Validates that components {@link String} value (its letters) are all upper
 * case.
 */
public class LBJUpperCaseValidator implements LBJValueValidator<String>, SimpleBean<LBJUpperCaseValidator> {

	@Override
	public boolean isValid(String value) {
		return StringUtils.isAllUpperCase(value.replaceAll("\\P{L}+", ""));
	}

}
