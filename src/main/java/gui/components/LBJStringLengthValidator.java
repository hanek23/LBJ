package gui.components;

import org.apache.commons.lang3.StringUtils;

import constants.Settings;

public class LBJStringLengthValidator implements LBJValidator<String> {

	@Override
	public boolean isValid(String value) {
		return StringUtils.length(value) <= Settings.MAX_LENGTH_OF_NAMES;
	}

}
