package gui.suppliers;

import constants.NamingConventions.LetterCase;
import gui.components.LBJValueComponent;
import gui.updaters.LBJValueUpdater;
import gui.updaters.shared.LBJActivatorComponentUpdater;
import gui.updaters.shared.LBJDeactivatorComponentUpdater;
import gui.updaters.shared.LBJLowerCaseUpdater;
import gui.updaters.shared.LBJRequiredLabelComponentUpdater;
import gui.updaters.shared.LBJUpperCaseUpdater;

/**
 * Static supplier of all {@link LBJValueUpdater}s.
 */
public class LBJUpdaterSupplier {

	// SHARED
	private static final LBJLowerCaseUpdater LOWER_CASE_UPDATER = new LBJLowerCaseUpdater();
	private static final LBJUpperCaseUpdater UPPER_CASE_UPDATER = new LBJUpperCaseUpdater();
	private static final LBJActivatorComponentUpdater ACTIVATOR_COMPONENT_UPDATER = new LBJActivatorComponentUpdater();
	private static final LBJDeactivatorComponentUpdater DEACTIVATOR_COMPONENT_UPDATER = new LBJDeactivatorComponentUpdater();
	private static final LBJRequiredLabelComponentUpdater REQUIRED_LABEL_COMPONENT_UPDATER = new LBJRequiredLabelComponentUpdater();
	private static final LBJValueUpdater<String> NO_UPDATER = new LBJValueUpdater<String>() {
		@Override
		public void update(LBJValueComponent<String> component) {
			// not updating anything
		}

	};

	private LBJUpdaterSupplier() {
		// only static access
	}

	/**
	 * @return if {@link LetterCase#UPPER} is passed the {@link LBJUpperCaseUpdater}
	 *         is returned. If {@link LetterCase#LOWER} is passed the
	 *         {@link LBJLowerCaseUpdater} is returned. if {@link LetterCase#NONE}
	 *         is passed the updater that does nothing is returned.
	 */
	public static LBJValueUpdater<String> caseUpdater(LetterCase letterCase) {
		if (LetterCase.LOWER == letterCase) {
			return LOWER_CASE_UPDATER;
		}
		if (LetterCase.UPPER == letterCase) {
			return UPPER_CASE_UPDATER;
		}
		return NO_UPDATER;
	}

	public static LBJLowerCaseUpdater getLowerCaseUpdater() {
		return LOWER_CASE_UPDATER;
	}

	public static LBJUpperCaseUpdater getUpperCaseUpdater() {
		return UPPER_CASE_UPDATER;
	}

	public static LBJActivatorComponentUpdater getActivatorComponentUpdater() {
		return ACTIVATOR_COMPONENT_UPDATER;
	}
	
	public static LBJDeactivatorComponentUpdater getDeactivatorComponentUpdater() {
		return DEACTIVATOR_COMPONENT_UPDATER;
	}

	public static LBJRequiredLabelComponentUpdater getRequiredLabelComponentUpdater() {
		return REQUIRED_LABEL_COMPONENT_UPDATER;
	}
}
