package gui.updaters;

import constants.NamingConventions.LetterCase;
import gui.components.LBJValueHolderComponent;
import gui.updaters.shared.LBJValueLowerCaseUpdater;
import gui.updaters.shared.LBJValueUpperCaseUpdater;

/**
 * Static supplier of all {@link LBJValueUpdater}s.
 */
public class LBJUpdaterSupplier {

	private static final LBJValueLowerCaseUpdater lowerCaseUpdater = new LBJValueLowerCaseUpdater();
	private static final LBJValueUpperCaseUpdater upperCaseUpdater = new LBJValueUpperCaseUpdater();
	private static final LBJValueUpdater<String> noUpdater = new LBJValueUpdater<String>() {
		@Override
		public void update(LBJValueHolderComponent<String> component) {
			// not updating anything
		}

	};

	private LBJUpdaterSupplier() {
		// only static access
	}

	/**
	 * @return if {@link LetterCase#UPPER} is passed the
	 *         {@link LBJValueUpperCaseUpdater} is returned. If
	 *         {@link LetterCase#LOWER} is passed the
	 *         {@link LBJValueLowerCaseUpdater} is returned. if
	 *         {@link LetterCase#NONE} is passed the updater that does nothing is
	 *         returned.
	 */
	public static LBJValueUpdater<String> caseUpdater(LetterCase letterCase) {
		if (LetterCase.LOWER == letterCase) {
			return lowerCaseUpdater;
		}
		if (LetterCase.UPPER == letterCase) {
			return upperCaseUpdater;
		}
		return noUpdater;
	}

}
