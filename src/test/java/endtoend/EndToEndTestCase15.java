package endtoend;

import gui.forms.dropindex.DropIndexForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Drop two indices, for all databases, only changesets, starting ID = 99
 */
public class EndToEndTestCase15 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = true;

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		DropIndexForm dropIndexForm1 = mainMenuForm.getDropIndexForm();
		dropIndexForm1.focus();

		// DROP INDEX 1
		LBJFormAssert.assertThat(dropIndexForm1).isFocused();
		LBJTestUtils.click(dropIndexForm1.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		dropIndexForm1.focus();
		LBJFormAssert.assertThat(dropIndexForm1).isFocused();

		LBJTestUtils.setValueOf(dropIndexForm1.getTableNameTextBox(), "LBJ_DROP_INDEX");
		LBJTestUtils.setValueOf(dropIndexForm1.getIndexNameTextBox(), "I_LBJ_DROP_INDEX_NOW");
		LBJFormAssert.assertThat(dropIndexForm1).isValid();

		// DROP INDEX 2
		LBJTestUtils.click(dropIndexForm1.getDropIndexButton());
		DropIndexForm dropIndexForm2 = (DropIndexForm) dropIndexForm1.getNextForm();

		// going back and forth
		LBJFormAssert.assertThat(dropIndexForm2).isFocused();
		LBJTestUtils.click(dropIndexForm2.getBackButton());
		LBJFormAssert.assertThat(dropIndexForm1).isFocused();
		LBJTestUtils.click(dropIndexForm1.getDropIndexButton());
		LBJFormAssert.assertThat(dropIndexForm2).isFocused();

		LBJTestUtils.setValueOf(dropIndexForm2.getTableNameTextBox(), "LBJ_DROP_INDEX_2");
		LBJTestUtils.setValueOf(dropIndexForm2.getIndexNameTextBox(), "I_LBJ_DROP_INDEX_2_RIGHT_NOW");
		LBJFormAssert.assertThat(dropIndexForm2).isValid();

		// GENERATE FORM
		LBJTestUtils.click(dropIndexForm2.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(dropIndexForm2.getWindow(), dropIndexForm2);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// only changesets
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// starting from 24
		LBJTestUtils.setValueOf(generateForm.getStartingIdTextBox(), "99");
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
