package gui.forms.droptable;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.DropTable;
import domain.Table;
import domain.TableOperation;
import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.utils.LBJFormUtils;

/**
 * Form for removing table. You have to specify table name and potentionaly
 * sequence name. Does not have any case updaters nor validators since you might
 * want to drop something that does not comply standards.
 */
public class DropTableForm extends LBJEntityForm<DropTable> {

	private LBJTextBox tableNameTextBox;
	private LBJCheckBox dropSequenceCheckBox;
	private LBJTextBox sequenceNameTextBox;

	private Button generateButton;

	public DropTableForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this).required().addLengthValidator().build();

		dropSequenceCheckBox = new LBJCheckBoxBuilder(Labels.TABLE_SEQUENCE_NAME, this).build();

		sequenceNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_SEQUENCE_NAME, this).required().addLengthValidator()
				.activatorComponent(dropSequenceCheckBox).disabled().build();
	}

	@Override
	public void initializeButtons() {
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, dropSequenceCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, sequenceNameTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public DropTable convert() {
		DropTable table = new Table(tableNameTextBox.getValue(), TableOperation.DROP);
		if (getDropSequenceCheckBox().isChecked()) {
			table.setSequenceName(sequenceNameTextBox.getValue());
		}
		return table;
	}

	@Override
	public String toString() {
		return Labels.DROP_TABLE_FORM;
	}

	public LBJTextBox getTableNameTextBox() {
		return tableNameTextBox;
	}

	public LBJTextBox getSequenceNameTextBox() {
		return sequenceNameTextBox;
	}

	public LBJCheckBox getDropSequenceCheckBox() {
		return dropSequenceCheckBox;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

}
