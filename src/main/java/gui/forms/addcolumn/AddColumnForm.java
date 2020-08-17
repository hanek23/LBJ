package gui.forms.addcolumn;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import domain.AddColumn;
import domain.Column;
import domain.ColumnOperation;
import domain.ForeignKey;
import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.utils.LBJFormUtils;

/**
 * <p>
 * Form for creating columns. You can use it directly from {@link MainMenuForm}
 * to add column to existing table or thru {@link CreateTableForm} for adding
 * new columns to newly created table.
 * </p>
 * <p>
 * You can choose table name, column name, data type (if you choose 'boolean',
 * it will be interpreted as char(1) for databases that requires it), if the
 * column is or it is not nullable, if it does have or does not have an index
 * and if it does or does not have a foreign key.
 * 
 */
public class AddColumnForm extends LBJEntityForm<AddColumn> {

	private LBJTextBox tableNameTextBox;
	private LBJTextBox columnNameTextBox;
	private LBJTextBox dataTypeTextBox;
	private LBJCheckBox nullableCheckBox;
	private LBJTextBox defaultValueTextBox;
	private LBJCheckBox indexCheckBox;
	private LBJTextBox indexNameTextBox;
	private LBJCheckBox foreignKeyCheckBox;
	private LBJTextBox referencedTableNameTextBox;
	private LBJTextBox referencedColumnNameTextBox;
	private LBJTextBox foreignKeyNameTextBox;

	private Button addColumnButton;
	private Button generateButton;

	public AddColumnForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameTextBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.TABLE_NAME_CASE).build();
		setTableNameIfPossible();

		columnNameTextBox = new LBJTextBoxBuilder(Labels.COLUMN_NAME, this).required().addLengthValidator()
				.addCaseUpdaterAndValidator(NamingConventions.COLUMN_NAME_CASE).build();

		dataTypeTextBox = new LBJTextBoxBuilder(Labels.COLUMN_DATA_TYPE, this).required().build();

		nullableCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_NULLABLE, this).checked().build();

		defaultValueTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_DEFAULT_VALUE, this)
				.deactivatorComponent(nullableCheckBox).disabled().build();

		indexCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_INDEX, this).build();

		indexNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_INDEX_NAME, this).required().addLengthValidator()
				.addNamingConventionUpdater(NamingConventions.getIndexName()).activatorComponent(indexCheckBox)
				.addCaseUpdaterAndValidator(NamingConventions.INDEX_NAME_CASE).disabled().build();

		foreignKeyCheckBox = new LBJCheckBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY, this).build();

		referencedTableNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_REFERENCED_TABLE, this).required()
				.addLengthValidator().activatorComponent(foreignKeyCheckBox)
				.addCaseUpdaterAndValidator(NamingConventions.TABLE_NAME_CASE).disabled().build();

		referencedColumnNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_REFERENCED_COLUMN, this).required()
				.addLengthValidator().activatorComponent(foreignKeyCheckBox)
				.addCaseUpdaterAndValidator(NamingConventions.COLUMN_NAME_CASE).disabled().build();

		foreignKeyNameTextBox = new LBJTextBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY_NAME, this).required()
				.addLengthValidator().addNamingConventionUpdater(NamingConventions.getForeignKeyName())
				.activatorComponent(foreignKeyCheckBox)
				.addCaseUpdaterAndValidator(NamingConventions.FOREIGN_KEY_NAME_CASE).disabled().build();

	}

	@Override
	public void initializeButtons() {
		addColumnButton = LBJFormUtils.createAddColumnButton(this, false);
		generateButton = LBJFormUtils.createGenerateButton(this);
	}

	/**
	 * If previousForm has Table name textBox, this method will copy that value into
	 * this form's table name
	 */
	private void setTableNameIfPossible() {
		if (getPreviousForm() instanceof CreateTableForm) {
			tableNameTextBox.setValue(((CreateTableForm) getPreviousForm()).getTableNameTextBox().getValue());
			return;
		}
		if (getPreviousForm() instanceof AddColumnForm) {
			tableNameTextBox.setValue(((AddColumnForm) getPreviousForm()).getTableNameTextBox().getValue());
			return;
		}
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, dataTypeTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, nullableCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, defaultValueTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, indexCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, indexNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, foreignKeyCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, referencedTableNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, referencedColumnNameTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, foreignKeyNameTextBox);
	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, addColumnButton);
		LBJFormUtils.addButtonTo(this, generateButton);
	}

	@Override
	public AddColumn convert() {
		AddColumn column = new Column(columnNameTextBox.getValue(), ColumnOperation.ADD);
		column.setTableName(tableNameTextBox.getValue());
		column.setDataType(dataTypeTextBox.getValue());
		column.setNullable(nullableCheckBox.getValue());
		if (column.isTypeBoolean()) {
			// boolean is false by default, user gets a chance to specify own default
			column.setDefaultValue("false");
		}
		if (!column.isNullable() && StringUtils.isNotBlank(defaultValueTextBox.getValue())) {
			column.setDefaultValue(defaultValueTextBox.getValue());
		}
		column.setIndex(indexCheckBox.getValue());
		column.setIndexName(indexNameTextBox.getValue());
		if (foreignKeyCheckBox.isChecked()) {
			ForeignKey key = new ForeignKey(foreignKeyNameTextBox.getValue());
			key.setReferencedColumn(referencedColumnNameTextBox.getValue());
			key.setReferencedTable(referencedTableNameTextBox.getValue());
			column.setForeignKey(key);
		}
		return column;
	}

	@Override
	public void run() {
		applyPreferences();
		super.run();
	}

	/**
	 * Applies potential new Naming convention as preferences could have been
	 * changed since last visit
	 */
	private void applyPreferences() {
		indexNameTextBox.setNamingConvention(NamingConventions.getIndexName());
		foreignKeyNameTextBox.setNamingConvention(NamingConventions.getForeignKeyName());
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

	public Button getAddColumnButton() {
		return addColumnButton;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

	public LBJTextBox getDefaultValueTextBox() {
		return defaultValueTextBox;
	}

	public void setDefaultValueTextBox(LBJTextBox defaultValueTextBox) {
		this.defaultValueTextBox = defaultValueTextBox;
	}

}
