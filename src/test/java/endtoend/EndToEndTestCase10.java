package endtoend;

import gui.forms.dropcolumn.DropColumnForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Drop column with index and foreign key, for all databases, whole changelog
 */
public class EndToEndTestCase10 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = false;
	private static final String TABLE_NAME = "LBJ_REFERENCE";
	private static final String COLUMN_NAME = "ACTION";
	private static final String INDEX_NAME = "I_ACTION";
	private static final String FK_NAME = "F_REF_ACTION";

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		DropColumnForm dropColumnForm = mainMenuForm.getDropColumnForm();
		dropColumnForm.focus();

		LBJFormAssert.assertThat(dropColumnForm).isFocused();
		LBJTestUtils.click(dropColumnForm.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		dropColumnForm.focus();
		LBJFormAssert.assertThat(dropColumnForm).isFocused();

		// Column
		LBJTestUtils.setValueOf(dropColumnForm.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(dropColumnForm.getColumnNameTextBox(), COLUMN_NAME);
		// with index
		LBJTestUtils.setValueOf(dropColumnForm.getIndexCheckBox(), true);
		LBJTestUtils.setValueOf(dropColumnForm.getIndexNameTextBox(), INDEX_NAME);
		// and foreign key
		LBJTestUtils.setValueOf(dropColumnForm.getForeignKeyCheckBox(), true);
		LBJTestUtils.setValueOf(dropColumnForm.getForeignKeyNameTextBox(), FK_NAME);

		LBJFormAssert.assertThat(dropColumnForm).isValid();
		LBJTestUtils.click(dropColumnForm.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(dropColumnForm.getWindow(), dropColumnForm);
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
