package endtoend;

import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import constants.NamingConventions;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.forms.removenotnullconstraint.RemoveNotNullConstraintForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * 1 table, 2 columns, for all databases, only changesets starting from ID 10,
 * but first clicking all kinds of buttons on all forms to test their
 * functionality
 */
public class EndToEndTest4 extends AbstractXmlSupplier implements EndToEndTest {

	private static final String TABLE_NAME = "TABLE_NAME";
	private static final boolean ONLY_CHANGESETS = true;

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		RemoveNotNullConstraintForm removeNotNullConstraintForm = mainMenuForm.getRemoveNotNullConstraintForm();
		removeNotNullConstraintForm.focus();
		LBJFormAssert.assertThat(removeNotNullConstraintForm).isFocused();
		LBJFormAssert.assertThat(removeNotNullConstraintForm).isNotValid();

		// Just fill this form and go back to main menu, final xml should not contain
		// this
		LBJTestUtils.setValueOf(removeNotNullConstraintForm.getTableNameTextBox(), "LBJ_RELATED_ID");
		LBJTestUtils.setValueOf(removeNotNullConstraintForm.getColumnNameTextBox(), "container");
		LBJTestUtils.setValueOf(removeNotNullConstraintForm.getDataTypeTextBox(), "integer");
		LBJFormAssert.assertThat(removeNotNullConstraintForm).isValid();
		LBJTestUtils.click(removeNotNullConstraintForm.getBackButton());

		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		CreateTableForm createTableForm = mainMenuForm.getCreateTableForm();
		createTableForm.focus();
		LBJFormAssert.assertThat(createTableForm).isFocused();
		LBJFormAssert.assertThat(createTableForm).isNotValid();

		// Fill just Table name, rest should be filled out by updaters
		LBJTestUtils.setValueOf(createTableForm.getTableNameTextBox(), "TABLE_NAME");
		assertThat(createTableForm.getPrimaryKeyNameTextBox())
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE + TABLE_NAME);
		assertThat(createTableForm.getPrimaryKeyConstraintNameTextBox())
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE + TABLE_NAME);
		assertThat(createTableForm.getSequenceNameTextBox())
				.isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME);

		// Going back to main menu and back to create table
		LBJFormAssert.assertThat(createTableForm).isValid();
		LBJTestUtils.click(createTableForm.getBackButton());

		// and values should have stayed same
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		createTableForm.focus();
		LBJFormAssert.assertThat(createTableForm).isFocused();
		assertThat(createTableForm.getPrimaryKeyNameTextBox())
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_NAME_DEFAULT_VALUE + TABLE_NAME);
		assertThat(createTableForm.getPrimaryKeyConstraintNameTextBox())
				.isEqualToIgnoringCase(NamingConventions.PRIMARY_KEY_CONSTRAINT_DEFAULT_VALUE + TABLE_NAME);
		assertThat(createTableForm.getSequenceNameTextBox())
				.isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME);
		LBJFormAssert.assertThat(createTableForm).isValid();

		// Go to add column
		LBJTestUtils.click(createTableForm.getAddColumnButton());
		AddColumnForm addColumnFormName = (AddColumnForm) createTableForm.getNextForm();
		LBJFormAssert.assertThat(addColumnFormName).isFocused();
		LBJFormAssert.assertThat(addColumnFormName).isNotValid();

		// Fill first column, table name should aready be prefilled
		assertThat(addColumnFormName.getTableNameTextBox()).isEqualToIgnoringCase(TABLE_NAME);
		LBJTestUtils.setValueOf(addColumnFormName.getColumnNameTextBox(), "actionRef");
		LBJTestUtils.setValueOf(addColumnFormName.getDataTypeTextBox(), "integer");
		// nullable
		LBJTestUtils.setValueOf(addColumnFormName.getNullableCheckBox(), true);
		// with index
		LBJTestUtils.setValueOf(addColumnFormName.getIndexCheckBox(), true);
		// and foreign key
		LBJTestUtils.setValueOf(addColumnFormName.getForeignKeyCheckBox(), true);
		LBJTestUtils.setValueOf(addColumnFormName.getReferencedTableNameTextBox(), "LBJ_TABLE");
		LBJTestUtils.setValueOf(addColumnFormName.getReferencedColumnNameTextBox(), "id_lbj_table");
		LBJTestUtils.setValueOf(addColumnFormName.getIndexNameTextBox(), "");
		LBJTestUtils.setValueOf(addColumnFormName.getForeignKeyNameTextBox(), "");
		// Form is not valid because of missing index name and foreign key name so
		// clicking on button should have no effect
		LBJFormAssert.assertThat(addColumnFormName).isNotValid();
		LBJTestUtils.click(addColumnFormName.getAddColumnButton());
		LBJFormAssert.assertThat(addColumnFormName).isFocused();

		// Fill remaining
		LBJTestUtils.setValueOf(addColumnFormName.getIndexNameTextBox(), "INDEX_NAME");
		LBJTestUtils.setValueOf(addColumnFormName.getForeignKeyNameTextBox(), "FOREIGN_KEY_NAME");
		// So now it should be valid and clicking on addColumn should get us to another
		// form
		LBJFormAssert.assertThat(addColumnFormName).isValid();
		LBJTestUtils.click(addColumnFormName.getAddColumnButton());

		AddColumnForm addColumnFormIsAlive = (AddColumnForm) addColumnFormName.getNextForm();
		LBJFormAssert.assertThat(addColumnFormIsAlive).isNotValid();
		// boolean column
		assertThat(addColumnFormName.getTableNameTextBox()).isEqualToIgnoringCase(TABLE_NAME);
		LBJTestUtils.setValueOf(addColumnFormIsAlive.getColumnNameTextBox(), "isAlive");
		// because of missing data type
		LBJFormAssert.assertThat(addColumnFormIsAlive).isNotValid();
		LBJTestUtils.setValueOf(addColumnFormIsAlive.getDataTypeTextBox(), "boolean");
		LBJFormAssert.assertThat(addColumnFormIsAlive).isValid();

		// Go to generate form
		LBJTestUtils.click(addColumnFormIsAlive.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(addColumnFormIsAlive.getWindow(),
				addColumnFormIsAlive);
		LBJFormAssert.assertThat(generateForm).isFocused();
		LBJFormAssert.assertThat(generateForm).isNotValid();

		// only changesets
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// starting from 10
		LBJTestUtils.setValueOf(generateForm.getStartingIdTextBox(), "10");
		// for all databases
		LBJTestUtils.setValueOf(generateForm.getOracleCheckBox(), true);
		LBJTestUtils.setValueOf(generateForm.getMssqlCheckBox(), true);
		LBJTestUtils.setValueOf(generateForm.getPostgreCheckBox(), true);
		// because of missing author
		LBJFormAssert.assertThat(generateForm).isNotValid();
		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		LBJFormAssert.assertThat(generateForm).isValid();
		LBJTestUtils.click(generateForm.getGenerateButton());
		LBJTestUtils.click(generateForm.getCopyToClipboardButton());

	}

	@Override
	public boolean checkXsd() {
		return !ONLY_CHANGESETS;
	}

}
