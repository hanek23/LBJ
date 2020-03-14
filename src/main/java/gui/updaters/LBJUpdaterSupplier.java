package gui.updaters;

import gui.updaters.shared.LBJValueLowerCaseUpdater;
import gui.updaters.shared.LBJValueUpperCaseUpdater;

public class LBJUpdaterSupplier {

	public static final LBJValueLowerCaseUpdater lowerCaseUpdater = new LBJValueLowerCaseUpdater();
	public static final LBJValueUpperCaseUpdater upperCaseUpdater = new LBJValueUpperCaseUpdater();

	private LBJUpdaterSupplier() {
		// only static access
	}

}
