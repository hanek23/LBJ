package gui.suppliers;

import constants.NamingConventions.LetterCase;
import gui.components.LBJValueHolderComponent;
import gui.forms.createtable.LBJCreateTableFormPrimaryKeyUpdater;
import gui.forms.createtable.LBJCreateTableFormSequenceNameUpdater;
import gui.updaters.LBJValueUpdater;
import gui.updaters.shared.LBJLowerCaseUpdater;
import gui.updaters.shared.LBJUpperCaseUpdater;

/**
 * Static supplier of all {@link LBJValueUpdater}s.
 */
public class LBJUpdaterSupplier {

	// CREATE TABLE
	private static final LBJCreateTableFormPrimaryKeyUpdater CREATE_TABLE_PRIMARY_KEY_UPDATER = new LBJCreateTableFormPrimaryKeyUpdater();
	private static final LBJCreateTableFormSequenceNameUpdater CREATE_TABLE_SEQUENCE_NAME_UPDATER = new LBJCreateTableFormSequenceNameUpdater();

	// SHARED
	private static final LBJLowerCaseUpdater LOWER_CASE_UPDATER = new LBJLowerCaseUpdater();
	private static final LBJUpperCaseUpdater UPPER_CASE_UPDATER = new LBJUpperCaseUpdater();
	private static final LBJValueUpdater<String> NO_UPDATER = new LBJValueUpdater<String>() {
		@Override
		public void update(LBJValueHolderComponent<String> component) {
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

	public static LBJCreateTableFormPrimaryKeyUpdater getCreateTablePrimaryKeyUpdater() {
		return CREATE_TABLE_PRIMARY_KEY_UPDATER;
	}

	public static LBJCreateTableFormSequenceNameUpdater getCreateTableSequenceNameUpdater() {
		return CREATE_TABLE_SEQUENCE_NAME_UPDATER;
	}

	public static LBJLowerCaseUpdater getLowerCaseUpdater() {
		return LOWER_CASE_UPDATER;
	}

	public static LBJUpperCaseUpdater getUpperCaseUpdater() {
		return UPPER_CASE_UPDATER;
	}

}
