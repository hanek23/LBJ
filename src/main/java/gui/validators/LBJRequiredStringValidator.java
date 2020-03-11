package gui.validators;

import org.apache.commons.lang3.StringUtils;

public class LBJRequiredStringValidator implements LBJValidator<String> {

	@Override
	public boolean isValid(String value) {
		return !StringUtils.isBlank(value);
	}

}
