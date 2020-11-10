package gui.forms.dropindex;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.Column;
import domain.ColumnOperation;
import domain.DropIndex;
import gui.attribute.Attribute;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.utils.LBJFormUtils;

public class DropIndexForm extends LBJEntityForm<DropIndex> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox indexNameTextBox;

	private Button dropIndexButton;
	private Button generateButton;

	public DropIndexForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Attribute.TABLE_NAME, this).build();
		indexNameTextBox = new LBJTextBoxBuilder(Attribute.INDEX_NAME, this).clearNamingConvention().build();
	}

	@Override
	public void initializeButtons() {
		dropIndexButton = LBJFormUtils.dropAnotherIndexButton(this);
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, indexNameTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, dropIndexButton);
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public DropIndex convert() {
		DropIndex column = new Column(null, ColumnOperation.DROP_INDEX);
		column.setTableName(tableNameTextBox.getValue());
		column.setIndexName(indexNameTextBox.getValue());
		return column;
	}

	@Override
	public String toString() {
		return Labels.DROP_INDEX_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public LBJTextBox getIndexNameTextBox() {
		return indexNameTextBox;
	}

	public Button getDropIndexButton() {
		return dropIndexButton;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

}
