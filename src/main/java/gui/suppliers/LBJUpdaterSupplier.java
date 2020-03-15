package gui.suppliers;

import constants.NamingConventions.LetterCase;
import gui.components.LBJValueHolderComponent;
import gui.updaters.LBJValueUpdater;
import gui.updaters.shared.LBJLowerCaseUpdater;
import gui.updaters.shared.LBJUpperCaseUpdater;

/**
 * Static supplier of all {@link LBJValueUpdater}s.
 */
public class LBJUpdaterSupplier {

	private static final LBJLowerCaseUpdater lowerCaseUpdater = new LBJLowerCaseUpdater();
	private static final LBJUpperCaseUpdater upperCaseUpdater = new LBJUpperCaseUpdater();
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
	 *         {@link LBJUpperCaseUpdater} is returned. If
	 *         {@link LetterCase#LOWER} is passed the
	 *         {@link LBJLowerCaseUpdater} is returned. if
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
