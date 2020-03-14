package gui.validators;

import constants.NamingConventions.LetterCase;
import gui.updaters.LBJValueUpdater;
import gui.updaters.shared.LBJValueUpperCaseUpdater;
import gui.validators.shared.LBJLowerCaseValidator;
import gui.validators.shared.LBJRequiredStringValidator;
import gui.validators.shared.LBJStringLengthValidator;
import gui.validators.shared.LBJUpperCaseValidator;

/**
 * Static supplier of all {@link LBJValueUpdater}s.
 */
public class LBJValidatorSupplier {

	public static final LBJLowerCaseValidator lowerCaseValidator = new LBJLowerCaseValidator();
	public static final LBJUpperCaseValidator upperCaseValidator = new LBJUpperCaseValidator();
	public static final LBJRequiredStringValidator stringRequiredValidator = new LBJRequiredStringValidator();
	public static final LBJStringLengthValidator stringLengthValidator = new LBJStringLengthValidator();
	private static final LBJValueValidator<String> noValidator = new LBJValueValidator<String>() {
		@Override
		public boolean isValid(String value) {
			// not validating anything
			return true;
		}

	};

	private LBJValidatorSupplier() {
		// only static access
	}

	/**
	 * @return if {@link LetterCase#UPPER} is passed the
	 *         {@link LBJValueUpperCaseUpdater} is returned. If
	 *         {@link LetterCase#LOWER} is passed the {@link LBJLowerCaseValidator}
	 *         is returned. if {@link LetterCase#NONE} is passed the validator that
	 *         does nothing is returned.
	 */
	public static LBJValueValidator<String> caseValidator(LetterCase letterCase) {
		if (LetterCase.LOWER == letterCase) {
			return lowerCaseValidator;
		}
		if (LetterCase.UPPER == letterCase) {
			return upperCaseValidator;
		}
		return noValidator;
	}

}
