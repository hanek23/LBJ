package gui.suppliers;

import com.googlecode.lanterna.gui2.Window;

import gui.forms.LBJForm;
import gui.forms.addcolumn.LBJAddColumnForm;
import gui.forms.createtable.LBJCreateTableForm;
import gui.forms.mainmenu.LBJMainMenuForm;

public class LBJFormSupplier {

	private LBJFormSupplier() {
		// static supplier
	}

	private static LBJMainMenuForm mainMenuForm;
	private static LBJCreateTableForm createTableForm;

	/**
	 * @return One and only instance of {@link LBJMainMenuForm}.
	 */
	public static LBJMainMenuForm getMainMenuForm() {
		if (mainMenuForm == null) {
			mainMenuForm = new LBJMainMenuForm();
		}
		return mainMenuForm;
	}

	/**
	 * @return One and only instance of {@link LBJCreateTableForm}.
	 */
	public static LBJCreateTableForm getCreateTableForm(Window window, LBJForm previousForm,
			boolean addAsUpdatableForm) {
		if (createTableForm == null) {
			createTableForm = new LBJCreateTableForm(window, previousForm);
			if (addAsUpdatableForm) {
				mainMenuForm.addFormToUpdate(createTableForm);
			}
		}
		return createTableForm;
	}

	public static LBJCreateTableForm getCreateTableForm(Window window, LBJForm previousForm) {
		return getCreateTableForm(window, previousForm, true);
	}

	/**
	 * @return Always new instance of {@link LBJAddColumnForm}.
	 */
	public static LBJAddColumnForm getAddColumnForm(Window window, LBJForm previousForm, boolean addAsUpdatableForm) {
		LBJAddColumnForm form = new LBJAddColumnForm(window, previousForm);
		if (addAsUpdatableForm) {
			mainMenuForm.addFormToUpdate(form);
		}
		return form;
	}

	public static LBJAddColumnForm getAddColumnForm(Window window, LBJForm previousForm) {
		return getAddColumnForm(window, previousForm, true);
	}

}
