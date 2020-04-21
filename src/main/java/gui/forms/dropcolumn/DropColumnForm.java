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
import gui.utils.LBJFormUtils;

/**
 * Form for droping column. You have to specify table name, column name and
 * potentionaly index and foreign key names. Does not have any case updaters nor
 * validators since you might want to drop something that does not comply
 * standards.
 */
public class DropColumnForm extends LBJEntityForm<DropColumn> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
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
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this).required().addLengthValidator().build();

		columnNameTextBox = new LBJTextBoxBuilder(Labels.COLUMN_NAME, this).required().addLengthValidator().build();

		dropIndexCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_INDEX, this).build();

		indexNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_INDEX_NAME, this).required().addLengthValidator()
				.activatorComponent(dropIndexCheckBox).disabled().build();

		dropForeignKeyCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY, this).build();

		foreignKeyNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY_NAME, this).required()
				.addLengthValidator().activatorComponent(dropForeignKeyCheckBox).disabled().build();

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
		LBJFormUtils.addComponentTo(this, tableNameTextBox);
		LBJFormUtils.addComponentTo(this, columnNameTextBox);
		LBJFormUtils.addComponentTo(this, dropIndexCheckBox);
		LBJFormUtils.addComponentTo(this, indexNameTextBox);
		LBJFormUtils.addComponentTo(this, dropForeignKeyCheckBox);
		LBJFormUtils.addComponentTo(this, foreignKeyNameTextBox);
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
		column.setIndex(dropIndexCheckBox.getValue());
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

}
