package endtoend;

import gui.forms.addcolumn.AddColumnForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * One boolean column only for postgre, only changesets, starting ID = 99
 */
public class EndToEndTest7 extends AbstractXmlSupplier implements EndToEndTest {

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
		LBJTestUtils.setValueOf(addColumnForm.getColumnNameTextBox(), "forPostgre");
		LBJTestUtils.setValueOf(addColumnForm.getDataTypeTextBox(), "boolean");
		LBJFormAssert.assertThat(addColumnForm).isValid();

		LBJTestUtils.click(addColumnForm.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(addColumnForm.getWindow(), addColumnForm);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// only changesets
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// starting from 99
		LBJTestUtils.setValueOf(generateForm.getStartingIdTextBox(), "99");
		// only for postgre
		LBJTestUtils.setValueOf(generateForm.getOracleCheckBox(), false);
		LBJTestUtils.setValueOf(generateForm.getMssqlCheckBox(), false);
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
