package endtoend;

import gui.forms.addcolumn.AddColumnForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * One boolean column only for mssql, only changesets, starting ID = 2
 */
public class EndToEndTestCase6 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = true;

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		AddColumnForm addColumnForm = mainMenuForm.getAddColumnForm();
		addColumnForm.focus();

		LBJFormAssert.assertThat(addColumnForm).isFocused();
		LBJTestUtils.click(addColumnForm.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		addColumnForm.focus();
		LBJFormAssert.assertThat(addColumnForm).isFocused();

		// boolean column
		LBJTestUtils.setValueOf(addColumnForm.getTableNameTextBox(), "LBJ_REFERENCE");
		LBJTestUtils.setValueOf(addColumnForm.getColumnNameTextBox(), "forMssql");
		LBJTestUtils.setValueOf(addColumnForm.getDataTypeTextBox(), "boolean");
		LBJFormAssert.assertThat(addColumnForm).isValid();

		LBJTestUtils.click(addColumnForm.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(addColumnForm.getWindow(), addColumnForm);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// only changesets
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// starting from 2
		LBJTestUtils.setValueOf(generateForm.getStartingIdTextBox(), "2");
		// only for mssql
		LBJTestUtils.setValueOf(generateForm.getOracleCheckBox(), false);
		LBJTestUtils.setValueOf(generateForm.getMssqlCheckBox(), true);
		LBJTestUtils.setValueOf(generateForm.getPostgreCheckBox(), false);

		LBJFormAssert.assertThat(generateForm).isValid();
		LBJTestUtils.click(generateForm.getGenerateButton());
		LBJTestUtils.click(generateForm.getCopyToClipboardButton());
	}

	@Override
	public boolean checkXsd() {
		return !ONLY_CHANGESETS;
	}

}
