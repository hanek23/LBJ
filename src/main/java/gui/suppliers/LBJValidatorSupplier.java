package gui.suppliers;

import constants.NamingConventions.LetterCase;
import gui.forms.createtable.LBJCreateTableFormDatabasesValidator;
import gui.updaters.LBJValueUpdater;
import gui.updaters.shared.LBJUpperCaseUpdater;
import gui.validators.LBJValueValidator;
import gui.validators.shared.LBJLowerCaseValidator;
import gui.validators.shared.LBJStringRequiredValidator;
import gui.validators.shared.LBJStringLengthValidator;
import gui.validators.shared.LBJUpperCaseValidator;

/**
 * Static supplier of all {@link LBJValueUpdater}s.
 */
public class LBJValidatorSupplier {

	// CREATE TABLE
	private static final LBJCreateTableFormDatabasesValidator CREATE_TABLE_DATABASES_VALIDATOR = new LBJCreateTableFormDatabasesValidator();

	// SHARED
	private static final LBJLowerCaseValidator LOWER_CASE_VALIDATOR = new LBJLowerCaseValidator();
	private static final LBJUpperCaseValidator UPPER_CASE_VALIDATOR = new LBJUpperCaseValidator();
	private static final LBJStringRequiredValidator STRING_REQUIRED_VALIDATOR = new LBJStringRequiredValidator();
	private static final LBJStringLengthValidator STRING_LENGTH_VALIDATOR = new LBJStringLengthValidator();
	private static final LBJValueValidator<String> NO_VALIDATOR = new LBJValueValidator<String>() {
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
	 * @return if {@link LetterCase#UPPER} is passed the {@link LBJUpperCaseUpdater}
	 *         is returned. If {@link LetterCase#LOWER} is passed the
	 *         {@link LBJLowerCaseValidator} is returned. if {@link LetterCase#NONE}
	 *         is passed the validator that does nothing is returned.
	 */
	public static LBJValueValidator<String> getCaseValidator(LetterCase letterCase) {
		if (LetterCase.LOWER == letterCase) {
			return LOWER_CASE_VALIDATOR;
		}
		if (LetterCase.UPPER == letterCase) {
			return UPPER_CASE_VALIDATOR;
		}
		return NO_VALIDATOR;
	}

	public static LBJStringRequiredValidator getStringRequiredValidator() {
		return STRING_REQUIRED_VALIDATOR;
	}

	public static LBJStringLengthValidator getStringLengthValidator() {
		return STRING_LENGTH_VALIDATOR;
	}

	public static LBJLowerCaseValidator getLowerCasevalidator() {
		return LOWER_CASE_VALIDATOR;
	}

	public static LBJUpperCaseValidator getUpperCasevalidator() {
		return UPPER_CASE_VALIDATOR;
	}

	public static LBJCreateTableFormDatabasesValidator getCreateTableDatabasesValidator() {
		return CREATE_TABLE_DATABASES_VALIDATOR;
	}

}
