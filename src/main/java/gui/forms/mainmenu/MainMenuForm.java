package gui.forms.mainmenu;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import gui.builders.LBJPlainLabelBuilder;
import gui.components.LBJPlainLabel;
import gui.forms.LBJMainMenuForm;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.dropcolumn.DropColumnForm;
import gui.forms.dropnotnullconstraint.DropNotNullConstraintForm;
import gui.forms.droptable.DropTableForm;
import gui.forms.preferences.PreferencesForm;
import gui.suppliers.LBJFormSupplier;
import gui.utils.LBJFormUtils;

/**
 * @see LBJMainMenuForm
 */
public class MainMenuForm extends LBJMainMenuForm {

	private LBJPlainLabel questionLabel;
	private ActionListBox mainMenu;
	private CreateTableForm createTableForm;
	private DropTableForm dropTableForm;
	private AddColumnForm addColumnForm;
	private DropColumnForm dropColumnForm;
	private DropNotNullConstraintForm dropNotNullConstraintForm;
	private PreferencesForm preferencesForm;

	public MainMenuForm() {
		this(new BasicWindow(Labels.WINDOW_NAME));
	}

	private MainMenuForm(Window window) {
		super(window);
		initialize();
	}

	@Override
	public String toString() {
		return Labels.MAIN_MENU_FORM;
	}

	@Override
	public void initializeComponents() {
		mainMenu = new ActionListBox();
		questionLabel = new LBJPlainLabelBuilder(Labels.MAIN_MENU_QUESTION, this).build();
		createTableForm = LBJFormSupplier.getCreateTableForm(getWindow(), this, false);
		dropTableForm = LBJFormSupplier.getDropTableForm(getWindow(), this, false);
		addColumnForm = LBJFormSupplier.getAddColumnForm(getWindow(), this, false);
		dropColumnForm = LBJFormSupplier.getDropColumnForm(getWindow(), this, false);
		dropNotNullConstraintForm = LBJFormSupplier.getDropNotNullConstraintForm(getWindow(), this, false);
		preferencesForm = LBJFormSupplier.getPreferencesForm(getWindow(), this, false);

		addFormToUpdate(createTableForm);
		addFormToUpdate(dropTableForm);
		addFormToUpdate(addColumnForm);
		addFormToUpdate(dropColumnForm);
		addFormToUpdate(dropNotNullConstraintForm);
		addFormToUpdate(preferencesForm);
	}

	@Override
	public void addComponents() {
		LBJFormUtils.addLabelToMainMenuContent(getContent(), questionLabel);
		LBJFormUtils.addMenuToMainMenuContent(getContent(), mainMenu);
		LBJFormUtils.addItemToMenu(mainMenu, createTableForm, Labels.MAIN_MENU_CREATE_TABLE);
		LBJFormUtils.addItemToMenu(mainMenu, dropTableForm, Labels.MAIN_MENU_DROP_TABLE);
		LBJFormUtils.addItemToMenu(mainMenu, addColumnForm, Labels.MAIN_MENU_ADD_COLUMN);
		LBJFormUtils.addItemToMenu(mainMenu, dropColumnForm, Labels.MAIN_MENU_DROP_COLUMN);
		LBJFormUtils.addItemToMenu(mainMenu, dropNotNullConstraintForm, Labels.MAIN_MENU_DROP_NOT_NULL_CONSTRAINT);
		LBJFormUtils.addItemToMenu(mainMenu, preferencesForm, Labels.MAIN_MENU_PREFERENCES);
		LBJFormUtils.addExitButtonToMainMenu(mainMenu);
	}

	public AddColumnForm getAddColumnForm() {
		return addColumnForm;
	}

	public DropNotNullConstraintForm getDropNotNullConstraintForm() {
		return dropNotNullConstraintForm;
	}

	public CreateTableForm getCreateTableForm() {
		return createTableForm;
	}

	public DropColumnForm getDropColumnForm() {
		return dropColumnForm;
	}

}
