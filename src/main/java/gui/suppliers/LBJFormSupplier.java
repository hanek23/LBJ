package gui.suppliers;

import com.googlecode.lanterna.gui2.Window;

import gui.forms.LBJForm;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.forms.preferences.PreferencesForm;
import gui.forms.removenotnullconstraint.RemoveNotNullConstraintForm;

/**
 * Static supplier of all {@link LBJForm}s
 */
public class LBJFormSupplier {

	private LBJFormSupplier() {
		// static supplier
	}

	private static MainMenuForm mainMenuForm;
	private static CreateTableForm createTableForm;
	private static GenerateForm generateForm;
	private static RemoveNotNullConstraintForm removeNotNullConstraintForm;
	private static PreferencesForm preferencesForm;

	/**
	 * @return One and only instance of {@link MainMenuForm}.
	 */
	public static synchronized MainMenuForm getMainMenuForm() {
		if (mainMenuForm == null) {
			mainMenuForm = new MainMenuForm();
		}
		return mainMenuForm;
	}

	/**
	 * @return One and only instance of {@link CreateTableForm}.
	 */
	public static synchronized CreateTableForm getCreateTableForm(Window window, LBJForm previousForm,
			boolean addAsUpdatableForm) {
		if (createTableForm == null) {
			createTableForm = new CreateTableForm(window, previousForm);
			if (addAsUpdatableForm) {
				mainMenuForm.addFormToUpdate(createTableForm);
			}
		}
		return createTableForm;
	}

	/**
	 * @return One and only instance of {@link GenerateForm}.
	 */
	public static synchronized GenerateForm getGenerateForm(Window window, LBJForm previousForm) {
		if (generateForm == null) {
			generateForm = new GenerateForm(window, previousForm);
			mainMenuForm.addFormToUpdate(generateForm);
		}
		// So every time the instance of this object points to requsted previous form
		generateForm.setPreviousForm(previousForm);
		return generateForm;
	}

	public static CreateTableForm getCreateTableForm(Window window, LBJForm previousForm) {
		return getCreateTableForm(window, previousForm, true);
	}

	/**
	 * @return Always new instance of {@link AddColumnForm}.
	 */
	public static AddColumnForm getAddColumnForm(Window window, LBJForm previousForm, boolean addAsUpdatableForm) {
		AddColumnForm form = new AddColumnForm(window, previousForm);
		if (addAsUpdatableForm) {
			mainMenuForm.addFormToUpdate(form);
		}
		return form;
	}

	public static AddColumnForm getAddColumnForm(Window window, LBJForm previousForm) {
		return getAddColumnForm(window, previousForm, true);
	}

	/**
	 * @return One and only instance of {@link RemoveNotNullConstraintForm}.
	 */
	public static synchronized RemoveNotNullConstraintForm getRemoveNotNullConstraintForm(Window window,
			LBJForm previousForm, boolean addAsUpdatableForm) {
		if (removeNotNullConstraintForm == null) {
			removeNotNullConstraintForm = new RemoveNotNullConstraintForm(window, previousForm);
			if (addAsUpdatableForm) {
				mainMenuForm.addFormToUpdate(removeNotNullConstraintForm);
			}
		}
		return removeNotNullConstraintForm;
	}

	public static PreferencesForm getPreferencesForm(Window window, LBJForm previousForm, boolean addAsUpdatableForm) {
		if (preferencesForm == null) {
			preferencesForm = new PreferencesForm(window, previousForm);
			if (addAsUpdatableForm) {
				mainMenuForm.addFormToUpdate(preferencesForm);
			}
		}
		return preferencesForm;
	}

	/**
	 * Only for testing!
	 */
	public static void reset() {
		mainMenuForm = null;
		createTableForm = null;
		removeNotNullConstraintForm = null;
		generateForm = null;
		preferencesForm = null;
	}

}
