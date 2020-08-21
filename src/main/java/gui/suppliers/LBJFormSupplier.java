package gui.suppliers;

import com.googlecode.lanterna.gui2.Window;

import gui.forms.LBJForm;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.dropcolumn.DropColumnForm;
import gui.forms.dropnotnullconstraint.DropNotNullConstraintForm;
import gui.forms.droptable.DropTableForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.forms.preferences.PreferencesForm;
import gui.utils.BeanSupplier;

/**
 * Static supplier of all {@link LBJForm}s
 */
public class LBJFormSupplier {

	private LBJFormSupplier() {
		// static supplier
	}

	private static CreateTableForm createTableForm;
	private static DropTableForm dropTableForm;
	private static GenerateForm generateForm;
	private static DropNotNullConstraintForm dropNotNullConstraintForm;
	private static PreferencesForm preferencesForm;

	/**
	 * @return One and only instance of {@link MainMenuForm}.
	 */
	public static synchronized MainMenuForm getMainMenuForm() {
		return BeanSupplier.get(MainMenuForm.class);
	}

	/**
	 * @return One and only instance of {@link CreateTableForm}.
	 */
	public static synchronized CreateTableForm getCreateTableForm(Window window, LBJForm previousForm,
			boolean addAsUpdatableForm) {
		if (createTableForm == null) {
			createTableForm = new CreateTableForm(window, previousForm);
			if (addAsUpdatableForm) {
				getMainMenuForm().addFormToUpdate(createTableForm);
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
			getMainMenuForm().addFormToUpdate(generateForm);
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
			getMainMenuForm().addFormToUpdate(form);
		}
		return form;
	}

	public static AddColumnForm getAddColumnForm(Window window, LBJForm previousForm) {
		return getAddColumnForm(window, previousForm, true);
	}

	/**
	 * @return Always new instance of {@link DropColumnForm}.
	 */
	public static DropColumnForm getDropColumnForm(Window window, LBJForm previousForm, boolean addAsUpdatableForm) {
		DropColumnForm form = new DropColumnForm(window, previousForm);
		if (addAsUpdatableForm) {
			getMainMenuForm().addFormToUpdate(form);
		}
		return form;
	}

	public static DropColumnForm getDropColumnForm(Window window, LBJForm previousForm) {
		return getDropColumnForm(window, previousForm, true);
	}

	/**
	 * @return One and only instance of {@link DropNotNullConstraintForm}.
	 */
	public static synchronized DropNotNullConstraintForm getDropNotNullConstraintForm(Window window,
			LBJForm previousForm, boolean addAsUpdatableForm) {
		if (dropNotNullConstraintForm == null) {
			dropNotNullConstraintForm = new DropNotNullConstraintForm(window, previousForm);
			if (addAsUpdatableForm) {
				getMainMenuForm().addFormToUpdate(dropNotNullConstraintForm);
			}
		}
		return dropNotNullConstraintForm;
	}

	public static PreferencesForm getPreferencesForm(Window window, LBJForm previousForm, boolean addAsUpdatableForm) {
		if (preferencesForm == null) {
			preferencesForm = new PreferencesForm(window, previousForm);
			if (addAsUpdatableForm) {
				getMainMenuForm().addFormToUpdate(preferencesForm);
			}
		}
		return preferencesForm;
	}

	/**
	 * @return One and only instance of {@link DropTableForm}.
	 */
	public static synchronized DropTableForm getDropTableForm(Window window, LBJForm previousForm,
			boolean addAsUpdatableForm) {
		if (dropTableForm == null) {
			dropTableForm = new DropTableForm(window, previousForm);
			if (addAsUpdatableForm) {
				getMainMenuForm().addFormToUpdate(createTableForm);
			}
		}
		return dropTableForm;
	}

	/**
	 * Only for testing!
	 */
	public static void reset() {
//		TODO
		BeanSupplier.init();
//		mainMenuForm = null;
		createTableForm = null;
		dropTableForm = null;
		dropNotNullConstraintForm = null;
		generateForm = null;
		preferencesForm = null;
	}

}
