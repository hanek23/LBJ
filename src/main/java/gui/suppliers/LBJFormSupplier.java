package gui.suppliers;

import com.googlecode.lanterna.gui2.Window;

import gui.forms.LBJForm;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.forms.removenotnullconstraint.RemoveNotNullConstraintForm;

public class LBJFormSupplier {

	private LBJFormSupplier() {
		// static supplier
	}

	private static final MainMenuForm mainMenuForm = new MainMenuForm();
	private static CreateTableForm createTableForm;
	private static GenerateForm generateForm;
	private static RemoveNotNullConstraintForm removeNotNullConstraintForm;

	/**
	 * @return One and only instance of {@link MainMenuForm}.
	 */
	public static MainMenuForm getMainMenuForm() {
		return mainMenuForm;
	}

	/**
	 * @return One and only instance of {@link CreateTableForm}.
	 */
	public static CreateTableForm getCreateTableForm(Window window, LBJForm previousForm, boolean addAsUpdatableForm) {
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
	public static GenerateForm getGenerateForm(Window window, LBJForm previousForm) {
		if (generateForm == null) {
			generateForm = new GenerateForm(window, previousForm);
			mainMenuForm.addFormToUpdate(generateForm);
		}
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

	/**
	 * @return One and only instance of {@link RemoveNotNullConstraintForm}.
	 */
	public static RemoveNotNullConstraintForm getRemoveNotNullConstraintForm(Window window, LBJForm previousForm,
			boolean addAsUpdatableForm) {
		if (removeNotNullConstraintForm == null) {
			removeNotNullConstraintForm = new RemoveNotNullConstraintForm(window, previousForm);
			if (addAsUpdatableForm) {
				mainMenuForm.addFormToUpdate(removeNotNullConstraintForm);
			}
		}
		return removeNotNullConstraintForm;
	}

	public static AddColumnForm getAddColumnForm(Window window, LBJForm previousForm) {
		return getAddColumnForm(window, previousForm, true);
	}

}
