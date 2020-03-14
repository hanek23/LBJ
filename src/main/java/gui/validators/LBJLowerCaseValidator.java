package gui.validators;

import org.apache.commons.lang3.StringUtils;

public class LBJLowerCaseValidator implements LBJValueValidator<String> {

	@Override
	public boolean isValid(String value) {
		return StringUtils.isAllLowerCase(value);
	}

}
