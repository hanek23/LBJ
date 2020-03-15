package gui.suppliers;

import com.googlecode.lanterna.gui2.Window;

import gui.forms.LBJForm;
import gui.forms.createtable.LBJCreateTableForm;
import gui.forms.mainmenu.LBJMainMenuForm;

public class LBJFormSupplier {

	private LBJFormSupplier() {
		// static supplier
	}

	private static LBJMainMenuForm mainMenuForm;
	private static LBJCreateTableForm createTableForm;

	public static LBJMainMenuForm getMainMenuForm() {
		if (mainMenuForm == null) {
			mainMenuForm = new LBJMainMenuForm();
		}
		return mainMenuForm;
	}

	public static LBJCreateTableForm getCreateTableForm(Window window, LBJForm previousForm) {
		if (createTableForm == null) {
			createTableForm = new LBJCreateTableForm(window, previousForm);
			getMainMenuForm().addFormToUpdate(mainMenuForm);
		}
		return createTableForm;
	}

}
