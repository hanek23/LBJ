package gui.validators;

import org.apache.commons.lang3.StringUtils;

public class LBJUpperCaseValidator implements LBJValueValidator<String> {

	@Override
	public boolean isValid(String value) {
		return StringUtils.isAllUpperCase(value);
	}

}
