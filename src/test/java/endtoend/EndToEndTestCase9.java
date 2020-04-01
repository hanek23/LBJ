package endtoend;

import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.forms.removenotnullconstraint.RemoveNotNullConstraintForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Removing not null constraint, for postgre, only changesets, starting ID = 123
 */
public class EndToEndTestCase9 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = true;

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		RemoveNotNullConstraintForm removeNotNullConstraintForm = mainMenuForm.getRemoveNotNullConstraintForm();
		removeNotNullConstraintForm.focus();

		LBJFormAssert.assertThat(removeNotNullConstraintForm).isFocused();
		LBJTestUtils.click(removeNotNullConstraintForm.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		removeNotNullConstraintForm.focus();
		LBJFormAssert.assertThat(removeNotNullConstraintForm).isFocused();

		LBJTestUtils.setValueOf(removeNotNullConstraintForm.getTableNameTextBox(), "LBJ_RELATED_ID");
		LBJTestUtils.setValueOf(removeNotNullConstraintForm.getColumnNameTextBox(), "container");
		LBJTestUtils.setValueOf(removeNotNullConstraintForm.getDataTypeTextBox(), "integer");
		LBJFormAssert.assertThat(removeNotNullConstraintForm).isValid();

		LBJTestUtils.click(removeNotNullConstraintForm.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(removeNotNullConstraintForm.getWindow(),
				removeNotNullConstraintForm);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// only changesets
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// starting from 123
		LBJTestUtils.setValueOf(generateForm.getStartingIdTextBox(), "123");
		// only for oracle
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
