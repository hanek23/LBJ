package gui.forms.mainmenu;

import com.googlecode.lanterna.gui2.BasicWindow;

import constants.Labels;
import gui.builders.LBJPlainLabelBuilder;
import gui.forms.LBJMainMenuForm;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.addnotnullconstraint.AddNotNullConstraintForm;
import gui.forms.createindex.CreateIndexForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.dropcolumn.DropColumnForm;
import gui.forms.dropindex.DropIndexForm;
import gui.forms.dropnotnullconstraint.DropNotNullConstraintForm;
import gui.forms.droptable.DropTableForm;
import gui.forms.modifydatatype.ModifyDataTypeForm;
import gui.forms.preferences.PreferencesForm;
import gui.suppliers.LBJFormSupplier;
import gui.utils.Bean;
import gui.utils.LBJFormUtils;
import main.LBJ;

/**
 * @see LBJMainMenuForm
 */
public class MainMenuForm extends LBJMainMenuForm implements Bean {

	private CreateTableForm createTableForm;
	private DropTableForm dropTableForm;
	private AddColumnForm addColumnForm;
	private DropColumnForm dropColumnForm;
	private DropNotNullConstraintForm dropNotNullConstraintForm;
	private AddNotNullConstraintForm addNotNullConstraintForm;
	private ModifyDataTypeForm modifyDataTypeForm;
	private CreateIndexForm createIndexForm;
	private DropIndexForm dropIndexForm;
	private PreferencesForm preferencesForm;

	public MainMenuForm() {
		super(new BasicWindow());
	}

	public void initializeBean() {
		((BasicWindow) getWindow()).setTitle(Labels.WINDOW_NAME + " v" + LBJ.properties.getVersion());
		initializeForm();
	}

	@Override
	public String toString() {
		return Labels.MAIN_MENU_FORM;
	}

	@Override
	public void initializeComponents() {
		super.initializeComponents();
		setQuestionLabel(new LBJPlainLabelBuilder(Labels.MAIN_MENU_QUESTION, this).build());
		createTableForm = LBJFormSupplier.getCreateTableForm(getWindow(), this, false);
		dropTableForm = LBJFormSupplier.getDropTableForm(getWindow(), this, false);
		addColumnForm = LBJFormSupplier.getAddColumnForm(getWindow(), this, false);
		dropColumnForm = LBJFormSupplier.getDropColumnForm(getWindow(), this, false);
		addNotNullConstraintForm = LBJFormSupplier.getAddNotNullConstraintForm(getWindow(), this, false);
		dropNotNullConstraintForm = LBJFormSupplier.getDropNotNullConstraintForm(getWindow(), this, false);
		modifyDataTypeForm = LBJFormSupplier.getModifyDataTypeForm(getWindow(), this, false);
		createIndexForm = LBJFormSupplier.getCreateIndexForm(getWindow(), this, false);
		dropIndexForm = LBJFormSupplier.getDropIndexForm(getWindow(), this, false);
		preferencesForm = LBJFormSupplier.getPreferencesForm(getWindow(), this, false);

		addFormToUpdate(createTableForm);
		addFormToUpdate(dropTableForm);
		addFormToUpdate(addColumnForm);
		addFormToUpdate(dropColumnForm);
		addFormToUpdate(addNotNullConstraintForm);
		addFormToUpdate(dropNotNullConstraintForm);
		addFormToUpdate(modifyDataTypeForm);
		addFormToUpdate(createIndexForm);
		addFormToUpdate(dropIndexForm);
		addFormToUpdate(preferencesForm);
	}

	@Override
	public void addComponents() {
		super.addComponents();
		LBJFormUtils.addItemToMenu(getMainMenu(), createTableForm, Labels.MAIN_MENU_CREATE_TABLE);
		LBJFormUtils.addItemToMenu(getMainMenu(), dropTableForm, Labels.MAIN_MENU_DROP_TABLE);
		LBJFormUtils.addItemToMenu(getMainMenu(), addColumnForm, Labels.MAIN_MENU_ADD_COLUMN);
		LBJFormUtils.addItemToMenu(getMainMenu(), dropColumnForm, Labels.MAIN_MENU_DROP_COLUMN);
		LBJFormUtils.addItemToMenu(getMainMenu(), addNotNullConstraintForm, Labels.MAIN_MENU_ADD_NOT_NULL_CONSTRAINT);
		LBJFormUtils.addItemToMenu(getMainMenu(), dropNotNullConstraintForm, Labels.MAIN_MENU_DROP_NOT_NULL_CONSTRAINT);
		LBJFormUtils.addItemToMenu(getMainMenu(), modifyDataTypeForm, Labels.MAIN_MENU_MODIFY_DATA_TYPE);
		LBJFormUtils.addItemToMenu(getMainMenu(), createIndexForm, Labels.MAIN_MENU_CREATE_INDEX);
		LBJFormUtils.addItemToMenu(getMainMenu(), dropIndexForm, Labels.MAIN_MENU_DROP_INDEX);
		LBJFormUtils.addItemToMenu(getMainMenu(), preferencesForm, Labels.MAIN_MENU_PREFERENCES);
		LBJFormUtils.addExitButtonToMenu(getMainMenu());
	}

	public AddColumnForm getAddColumnForm() {
		return addColumnForm;
	}

	public DropNotNullConstraintForm getDropNotNullConstraintForm() {
		return dropNotNullConstraintForm;
	}

	public AddNotNullConstraintForm getAddNotNullConstraintForm() {
		return addNotNullConstraintForm;
	}

	public CreateTableForm getCreateTableForm() {
		return createTableForm;
	}

	public DropColumnForm getDropColumnForm() {
		return dropColumnForm;
	}

	public ModifyDataTypeForm getModifyDataTypeForm() {
		return modifyDataTypeForm;
	}

	public CreateIndexForm getCreateIndexForm() {
		return createIndexForm;
	}

	public DropIndexForm getDropIndexForm() {
		return dropIndexForm;
	}

}
