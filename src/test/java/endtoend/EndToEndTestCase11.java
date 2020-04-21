package endtoend;

import gui.forms.dropcolumn.DropColumnForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Drop three columns one without fk nor index, one with just index and one with
 * just FK, whole changelog
 */
public class EndToEndTestCase11 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = false;
	private static final String TABLE_NAME = "LBJ_REFERENCE";
	private static final String COLUMN_NAME_1 = "ACTION";
	private static final String COLUMN_NAME_2 = "HEAD";
	private static final String COLUMN_NAME_3 = "PHONES";
	private static final String INDEX_NAME = "I_ACTION";
	private static final String FK_NAME = "F_REF_ACTION";

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		DropColumnForm dropColumnForm1 = mainMenuForm.getDropColumnForm();
		dropColumnForm1.focus();

		LBJFormAssert.assertThat(dropColumnForm1).isFocused();
		LBJTestUtils.click(dropColumnForm1.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		dropColumnForm1.focus();
		LBJFormAssert.assertThat(dropColumnForm1).isFocused();

		// Column withou index nor FK
		LBJTestUtils.setValueOf(dropColumnForm1.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(dropColumnForm1.getColumnNameTextBox(), COLUMN_NAME_1);
		LBJFormAssert.assertThat(dropColumnForm1).isValid();
		LBJTestUtils.click(dropColumnForm1.getDropColumnButton());

		DropColumnForm dropColumnForm2 = (DropColumnForm) dropColumnForm1.getNextForm();
		// Column with just index
		LBJTestUtils.setValueOf(dropColumnForm2.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(dropColumnForm2.getColumnNameTextBox(), COLUMN_NAME_2);
		LBJTestUtils.setValueOf(dropColumnForm2.getIndexCheckBox(), true);
		LBJTestUtils.setValueOf(dropColumnForm2.getIndexNameTextBox(), INDEX_NAME);
		LBJFormAssert.assertThat(dropColumnForm2).isValid();
		LBJTestUtils.click(dropColumnForm2.getDropColumnButton());

		DropColumnForm dropColumnForm3 = (DropColumnForm) dropColumnForm2.getNextForm();
		// Column with just foreign key
		LBJTestUtils.setValueOf(dropColumnForm3.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(dropColumnForm3.getColumnNameTextBox(), COLUMN_NAME_3);
		LBJTestUtils.setValueOf(dropColumnForm3.getForeignKeyCheckBox(), true);
		LBJTestUtils.setValueOf(dropColumnForm3.getForeignKeyNameTextBox(), FK_NAME);
		LBJFormAssert.assertThat(dropColumnForm3).isValid();

		// Generate
		LBJTestUtils.click(dropColumnForm3.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(dropColumnForm3.getWindow(), dropColumnForm3);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// whole changelog
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// for all databases
		LBJTestUtils.setValueOf(generateForm.getOracleCheckBox(), true);
		LBJTestUtils.setValueOf(generateForm.getMssqlCheckBox(), true);
		LBJTestUtils.setValueOf(generateForm.getPostgreCheckBox(), true);

		LBJFormAssert.assertThat(generateForm).isValid();
		LBJTestUtils.click(generateForm.getGenerateButton());
		LBJTestUtils.click(generateForm.getCopyToClipboardButton());
	}

	@Override
	public boolean checkXsd() {
		return !ONLY_CHANGESETS;
	}

}
