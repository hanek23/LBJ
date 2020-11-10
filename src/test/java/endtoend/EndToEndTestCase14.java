package endtoend;

import gui.forms.createindex.CreateIndexForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Creating index, for all databases, only changesets, starting ID = 24
 */
public class EndToEndTestCase14 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = true;

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		CreateIndexForm createIndexForm = mainMenuForm.getCreateIndexForm();
		createIndexForm.focus();

		LBJFormAssert.assertThat(createIndexForm).isFocused();
		LBJTestUtils.click(createIndexForm.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		createIndexForm.focus();
		LBJFormAssert.assertThat(createIndexForm).isFocused();

		LBJTestUtils.setValueOf(createIndexForm.getTableNameTextBox(), "LBJ_CREATE_INDEX");
		LBJTestUtils.setValueOf(createIndexForm.getColumnNameTextBox(), "please");
		LBJTestUtils.setValueOf(createIndexForm.getIndexNameTextBox(), "I_LBJ_CREATE_INDEX_PLEASE");
		LBJFormAssert.assertThat(createIndexForm).isValid();

		LBJTestUtils.click(createIndexForm.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(createIndexForm.getWindow(),
				createIndexForm);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// only changesets
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// starting from 24
		LBJTestUtils.setValueOf(generateForm.getStartingIdTextBox(), "24");
		// all databases
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
