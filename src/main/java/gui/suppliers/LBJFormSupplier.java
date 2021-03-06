package gui.suppliers;

import com.googlecode.lanterna.gui2.Window;

import gui.forms.LBJForm;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.addforeignkeyconstraint.AddForeignKeyConstraintForm;
import gui.forms.addnotnullconstraint.AddNotNullConstraintForm;
import gui.forms.createindex.CreateIndexForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.dropcolumn.DropColumnForm;
import gui.forms.dropforeignkeyconstraint.DropForeignKeyConstraintForm;
import gui.forms.dropindex.DropIndexForm;
import gui.forms.dropnotnullconstraint.DropNotNullConstraintForm;
import gui.forms.droptable.DropTableForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.forms.modifydatatype.ModifyDataTypeForm;
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
	private static AddNotNullConstraintForm addNotNullConstraintForm;
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

	/**
	 * @return Always new instance of {@link CreateIndexForm}.
	 */
	public static CreateIndexForm getCreateIndexForm(Window window, LBJForm previousForm, boolean addAsUpdatableForm) {
		CreateIndexForm form = new CreateIndexForm(window, previousForm);
		if (addAsUpdatableForm) {
			getMainMenuForm().addFormToUpdate(form);
		}
		return form;
	}

	/**
	 * @return Always new instance of {@link DropIndexForm}.
	 */
	public static DropIndexForm getDropIndexForm(Window window, LBJForm previousForm, boolean addAsUpdatableForm) {
		DropIndexForm form = new DropIndexForm(window, previousForm);
		if (addAsUpdatableForm) {
			getMainMenuForm().addFormToUpdate(form);
		}
		return form;
	}

	/**
	 * @return Always new instance of {@link AddForeignKeyConstraintForm}.
	 */
	public static AddForeignKeyConstraintForm getAddForeignKeyConstraintForm(Window window, LBJForm previousForm,
			boolean addAsUpdatableForm) {
		AddForeignKeyConstraintForm form = new AddForeignKeyConstraintForm(window, previousForm);
		if (addAsUpdatableForm) {
			getMainMenuForm().addFormToUpdate(form);
		}
		return form;
	}

	/**
	 * @return Always new instance of {@link DropForeignKeyConstraintForm}.
	 */
	public static DropForeignKeyConstraintForm getDropForeignKeyConstraintForm(Window window, LBJForm previousForm,
			boolean addAsUpdatableForm) {
		DropForeignKeyConstraintForm form = new DropForeignKeyConstraintForm(window, previousForm);
		if (addAsUpdatableForm) {
			getMainMenuForm().addFormToUpdate(form);
		}
		return form;
	}

	/**
	 * @return Always new instance of {@link ModifyDataTypeForm}.
	 */
	public static ModifyDataTypeForm getModifyDataTypeForm(Window window, LBJForm previousForm,
			boolean addAsUpdatableForm) {
		ModifyDataTypeForm form = new ModifyDataTypeForm(window, previousForm);
		if (addAsUpdatableForm) {
			getMainMenuForm().addFormToUpdate(form);
		}
		return form;
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

	/**
	 * @return One and only instance of {@link AddNotNullConstraintForm}.
	 */
	public static synchronized AddNotNullConstraintForm getAddNotNullConstraintForm(Window window, LBJForm previousForm,
			boolean addAsUpdatableForm) {
		if (addNotNullConstraintForm == null) {
			addNotNullConstraintForm = new AddNotNullConstraintForm(window, previousForm);
			if (addAsUpdatableForm) {
				getMainMenuForm().addFormToUpdate(addNotNullConstraintForm);
			}
		}
		return addNotNullConstraintForm;
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
		createTableForm = null;
		dropTableForm = null;
		generateForm = null;
		dropNotNullConstraintForm = null;
		addNotNullConstraintForm = null;
		preferencesForm = null;
	}

}
