package endtoend;

import gui.forms.addcolumn.AddColumnForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * One boolean column only for oracle, whole changelog
 */
public class EndToEndTestCase5 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = false;

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
		LBJTestUtils.setValueOf(addColumnForm.getColumnNameTextBox(), "forOracle");
		LBJTestUtils.setValueOf(addColumnForm.getDataTypeComboBox(), "boolean");
		LBJTestUtils.setValueOf(addColumnForm.getNullableCheckBox(), false);
		LBJFormAssert.assertThat(addColumnForm).isValid();

		LBJTestUtils.click(addColumnForm.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(addColumnForm.getWindow(), addColumnForm);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// whole changelog
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// only for oracle
		LBJTestUtils.setValueOf(generateForm.getOracleCheckBox(), true);
		LBJTestUtils.setValueOf(generateForm.getMssqlCheckBox(), false);
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
