package gui.validators.shared;

import org.apache.commons.lang3.StringUtils;

import constants.Settings;
import gui.utils.Bean;
import gui.utils.SimpleBean;
import gui.validators.LBJValueValidator;

/**
 * Validates that components {@link String} value length is not longer than
 * {@link Settings#MAX_LENGTH_OF_NAMES}
 */
public class LBJStringLengthValidator
		implements LBJValueValidator<String>, SimpleBean<LBJStringLengthValidator> {

	@Override
	public boolean isValid(String value) {
		return StringUtils.length(value) <= Settings.MAX_LENGTH_OF_NAMES;
	}

}
