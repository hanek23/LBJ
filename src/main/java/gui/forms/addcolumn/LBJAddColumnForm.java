package gui.forms.addcolumn;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.Column;
import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.forms.createtable.LBJCreateTableForm;
import gui.forms.mainmenu.LBJMainMenuForm;
import gui.suppliers.LBJFormSupplier;
import gui.suppliers.LBJUpdaterSupplier;
import gui.utils.LBJFormUtils;

/**
 * <p>
 * Form for creating columns. You can use it directly from
 * {@link LBJMainMenuForm} to add column to existing table or thru
 * {@link LBJCreateTableForm} for adding new columns to newly created table.
 * </p>
 * <p>
 * You can choose table name, column name, data type (if you choose 'boolean',
 * it will be interpreted as char(1) for databases that requires it), if the
 * column is or it is not nullable, if it does have or does not have an index
 * and if it does or does not have a foreign key.
 * 
 */
public class LBJAddColumnForm extends LBJEntityForm<Column> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJTextBox dataTypeTextBox;
	private LBJCheckBox nullableCheckBox;
	private LBJCheckBox indexCheckBox;
	private LBJTextBox indexNameTextBox;
	private LBJCheckBox foreignKeyCheckBox;
	private LBJTextBox referencedTableNameTextBox;
	private LBJTextBox referencedColumnNameTextBox;
	private LBJTextBox foreignKeyNameTextBox;

	private Button goToAddColumnButton;

	public LBJAddColumnForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.TABLE_NAME_CASE).build();

		columnNameTextBox = new LBJTextBoxBuilder(Labels.COLUMN_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.COLUMN_NAME_CASE).build();

		dataTypeTextBox = new LBJTextBoxBuilder(Labels.COLUMN_DATA_TYPE, this).required().build();

		nullableCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_NULLABLE, this).build();

		indexCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_INDEX, this).build();

		indexNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_INDEX_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.INDEX_NAME_CASE).disabled().build();

		foreignKeyCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY, this).build();

		referencedTableNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_REFERENCED_TABLE, this).required()
				.addLengthValidator().addCaseUpdaterAndValidator(NamingConventions.TABLE_NAME_CASE).disabled().build();

		referencedColumnNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_REFERENCED_COLUMN, this).required()
				.addLengthValidator().addCaseUpdaterAndValidator(NamingConventions.COLUMN_NAME_CASE).disabled().build();

		foreignKeyNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY_NAME, this).required()
				.addLengthValidator().addCaseUpdaterAndValidator(NamingConventions.FOREIGN_KEY_NAME_CASE).disabled()
				.build();

		goToAddColumnButton = new Button(Labels.BUTTON_ADD_ANOTHER_COLUMN);
		goToAddColumnButton.addListener(new Listener() {
			@Override
			public void onTriggered(Button button) {
				if (validate()) {
					if (getNextForm() == null) {
						setNextForm(LBJFormSupplier.getAddColumnForm(LBJAddColumnForm.this.getWindow(),
								LBJAddColumnForm.this));
					}
					goToNextForm();
				}

			}
		});
	}

	@Override
	public void initializeContent() {
		setContent(LBJFormUtils.initializeDefaultContent());
	}

	@Override
	public void addFormUpdaters() {
		addUpdater(LBJUpdaterSupplier.getAddColumnForeignKeyUpdater());
		addUpdater(LBJUpdaterSupplier.getAddColumnIndexNameUpdater());
	}

	@Override
	public void addFormValidators() {
		// no extra validators
	}

	@Override
	public void addComponentsToContent() {
		LBJFormUtils.addComponentToContent(getContent(), tableNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), columnNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), dataTypeTextBox);
		LBJFormUtils.addComponentToContent(getContent(), nullableCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), indexCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), indexNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), foreignKeyCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), referencedTableNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), referencedColumnNameTextBox);
		LBJFormUtils.addComponentToContent(getContent(), foreignKeyNameTextBox);
	}

	@Override
	public void addButtonsToContent() {
		LBJFormUtils.addEmptySpace(getContent());
		LBJFormUtils.addBackButton(getContent(), getPreviousForm());
		LBJFormUtils.addButtonToConent(getContent(), goToAddColumnButton);
	}

	@Override
	public Column convert() {
		return null;
	}

	@Override
	public String toString() {
		return Labels.ADD_COLUMN_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public LBJTextBox getColumnNameTextBox() {
		return columnNameTextBox;
	}

	public LBJTextBox getDataTypeTextBox() {
		return dataTypeTextBox;
	}

	public LBJCheckBox getNullableCheckBox() {
		return nullableCheckBox;
	}

	public LBJCheckBox getIndexCheckBox() {
		return indexCheckBox;
	}

	public LBJTextBox getIndexNameTextBox() {
		return indexNameTextBox;
	}

	public LBJCheckBox getForeignKeyCheckBox() {
		return foreignKeyCheckBox;
	}

	public LBJTextBox getReferencedTableNameTextBox() {
		return referencedTableNameTextBox;
	}

	public LBJTextBox getReferencedColumnNameTextBox() {
		return referencedColumnNameTextBox;
	}

	public LBJTextBox getForeignKeyNameTextBox() {
		return foreignKeyNameTextBox;
	}

}
