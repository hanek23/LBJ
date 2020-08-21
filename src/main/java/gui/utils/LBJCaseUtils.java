package gui.utils;

import constants.NamingConventions.LetterCase;
import gui.components.LBJValueComponent;
import gui.updaters.LBJValueUpdater;
import gui.updaters.shared.LBJLowerCaseUpdater;
import gui.updaters.shared.LBJUpperCaseUpdater;
import gui.validators.LBJValueValidator;
import gui.validators.shared.LBJLowerCaseValidator;
import gui.validators.shared.LBJUpperCaseValidator;

public class LBJCaseUtils {

	private LBJCaseUtils() {
		// only static access
	}

	private static final LBJValueValidator<String> NO_VALIDATOR = new LBJValueValidator<String>() {
		@Override
		public boolean isValid(String value) {
			// not validating anything
			return true;
		}

	};
	private static final LBJValueUpdater<String> NO_UPDATER = new LBJValueUpdater<String>() {
		@Override
		public void update(LBJValueComponent<String> component) {
			// not updating anything
		}

	};

	/**
	 * @return if {@link LetterCase#UPPER} is passed the {@link LBJUpperCaseUpdater}
	 *         is returned. If {@link LetterCase#LOWER} is passed the
	 *         {@link LBJLowerCaseValidator} is returned. if {@link LetterCase#NONE}
	 *         is passed the validator that does nothing is returned.
	 */
	public static LBJValueValidator<String> getCaseValidator(LetterCase letterCase) {
		if (LetterCase.LOWER == letterCase) {
			return BeanSupplier.get(LBJLowerCaseValidator.class);
		}
		if (LetterCase.UPPER == letterCase) {
			return BeanSupplier.get(LBJUpperCaseValidator.class);
		}
		return NO_VALIDATOR;
	}

	/**
	 * @return if {@link LetterCase#UPPER} is passed the {@link LBJUpperCaseUpdater}
	 *         is returned. If {@link LetterCase#LOWER} is passed the
	 *         {@link LBJLowerCaseUpdater} is returned. if {@link LetterCase#NONE}
	 *         is passed the updater that does nothing is returned.
	 */
	public static LBJValueUpdater<String> caseUpdater(LetterCase letterCase) {
		if (LetterCase.LOWER == letterCase) {
			return BeanSupplier.get(LBJLowerCaseUpdater.class);
		}
		if (LetterCase.UPPER == letterCase) {
			return BeanSupplier.get(LBJUpperCaseUpdater.class);
		}
		return NO_UPDATER;
	}

}
