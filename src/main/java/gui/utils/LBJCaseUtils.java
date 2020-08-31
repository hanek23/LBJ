package gui.utils;

import constants.NamingConventions.LetterCase;
import gui.updaters.LBJValueUpdater;
import gui.updaters.shared.LBJLowerCaseUpdater;
import gui.updaters.shared.LBJNoneCaseUpdater;
import gui.updaters.shared.LBJUpperCaseUpdater;
import gui.validators.LBJValueValidator;
import gui.validators.shared.LBJLowerCaseValidator;
import gui.validators.shared.LBJNoneCaseValidator;
import gui.validators.shared.LBJUpperCaseValidator;

public class LBJCaseUtils {

	private LBJCaseUtils() {
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
			return BeanSupplier.get(LBJLowerCaseValidator.class);
		}
		if (LetterCase.UPPER == letterCase) {
			return BeanSupplier.get(LBJUpperCaseValidator.class);
		}
		return BeanSupplier.get(LBJNoneCaseValidator.class);
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
		return BeanSupplier.get(LBJNoneCaseUpdater.class);
	}

}
