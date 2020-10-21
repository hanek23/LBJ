package gui.forms.dropcolumn;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.Column;
import domain.ColumnOperation;
import domain.DropColumn;
import domain.ForeignKey;
import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.utils.BeanSupplier;
import gui.utils.LBJFormUtils;
import main.LBJ;
import utils.LBJPreferences;

/**
 * Form for droping column. You have to specify table name, column name and
 * potentionaly index and foreign key names. You can also choose to drop default
 * value which is required to do on MSSQL if there is one. Does not have any
 * case updaters nor validators since you might want to drop something that does
 * not comply standards.
 */
public class DropColumnForm extends LBJEntityForm<DropColumn> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJCheckBox dropDefaultValueCheckBox;
	private LBJCheckBox dropIndexCheckBox;
	private LBJTextBox indexNameTextBox;
	private LBJCheckBox dropForeignKeyCheckBox;
	private LBJTextBox foreignKeyNameTextBox;

	private Button dropColumnButton;
	private Button generateButton;

	public DropColumnForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this).required()
				.addCaseUpdaterAndValidator(LBJ.preferences.getTableNameCase(),
						LBJ.preferences.getUseLetterCaseConventions())
				.addLengthValidator().build();

		if (LBJ.preferences.getCopyTableName()) {
			setTableNameIfPossible();
		}

		columnNameTextBox = new LBJTextBoxBuilder(Labels.COLUMN_NAME, this).required()
				.addCaseUpdaterAndValidator(LBJ.preferences.getColumnNameCase(),
						LBJ.preferences.getUseLetterCaseConventions())
				.addLengthValidator().build();

		dropDefaultValueCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_DEFAULT_VALUE, this).build();

		dropIndexCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_INDEX, this).build();

		indexNameTextBox = new LBJTextBoxBuilder(Labels.INDEX_NAME, this).required()
				.addCaseUpdaterAndValidator(LBJ.preferences.getIndexNameCase(),
						LBJ.preferences.getUseLetterCaseConventions())
				.addLengthValidator().activatorComponent(dropIndexCheckBox).disabled().build();

		dropForeignKeyCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY, this).build();

		foreignKeyNameTextBox = new LBJTextBoxBuilder(Labels.FOREIGN_KEY_NAME, this)
				.addCaseUpdaterAndValidator(LBJ.preferences.getForeignKeyNameCase(),
						LBJ.preferences.getUseLetterCaseConventions())
				.required().addLengthValidator().activatorComponent(dropForeignKeyCheckBox).disabled().build();

	}

	/**
	 * If previousForm has Table name textBox, this method will copy that value into
	 * this form's table name
	 */
	private void setTableNameIfPossible() {
		if (getPreviousForm() instanceof DropColumnForm) {
			tableNameTextBox.setValue(((DropColumnForm) getPreviousForm()).getTableNameTextBox().getValue());
			return;
		}
	}

	@Override
	public void initializeButtons() {
		dropColumnButton = LBJFormUtils.createDropColumnButton(this);
		generateButton = LBJFormUtils.createGenerateButton(this);
	}

	@Override
	public void addFormUpdaters() {
		// no form updaters
	}

	@Override
	public void addFormValidators() {
		// no form validators
	}

	@Override
	public void addComponents() {
		LBJFormUtils.addValueAndLabeledComponentTo(this, tableNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, columnNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, dropDefaultValueCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, dropIndexCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, indexNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, dropForeignKeyCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, foreignKeyNameTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, dropColumnButton);
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public DropColumn convert() {
		DropColumn column = new Column(columnNameTextBox.getValue(), ColumnOperation.DROP);
		column.setTableName(tableNameTextBox.getValue());
		column.setHasDefaultValue(dropDefaultValueCheckBox.getValue());
		column.setHasIndex(dropIndexCheckBox.getValue());
		column.setIndexName(indexNameTextBox.getValue());
		if (dropForeignKeyCheckBox.isChecked()) {
			ForeignKey key = new ForeignKey(foreignKeyNameTextBox.getValue());
			column.setForeignKey(key);
		}
		return column;
	}

	@Override
	public String toString() {
		return Labels.DROP_COLUMN_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public LBJTextBox getColumnNameTextBox() {
		return columnNameTextBox;
	}

	public LBJCheckBox getIndexCheckBox() {
		return dropIndexCheckBox;
	}

	public LBJTextBox getIndexNameTextBox() {
		return indexNameTextBox;
	}

	public LBJCheckBox getForeignKeyCheckBox() {
		return dropForeignKeyCheckBox;
	}

	public LBJTextBox getForeignKeyNameTextBox() {
		return foreignKeyNameTextBox;
	}

	public Button getDropColumnButton() {
		return dropColumnButton;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

	public LBJCheckBox getDropDefaultValueCheckBox() {
		return dropDefaultValueCheckBox;
	}

	public void setDropDefaultValueCheckBox(LBJCheckBox dropDefaultValueCheckBox) {
		this.dropDefaultValueCheckBox = dropDefaultValueCheckBox;
	}

}
