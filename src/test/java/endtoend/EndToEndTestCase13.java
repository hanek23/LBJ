package endtoend;

import gui.forms.addnotnullconstraint.AddNotNullConstraintForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Adding not null constraint, for all databases, only changesets, starting ID = 123
 */
public class EndToEndTestCase13 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = true;

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		AddNotNullConstraintForm addNotNullConstraintForm = mainMenuForm.getAddNotNullConstraintForm();
		addNotNullConstraintForm.focus();

		LBJFormAssert.assertThat(addNotNullConstraintForm).isFocused();
		LBJTestUtils.click(addNotNullConstraintForm.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		addNotNullConstraintForm.focus();
		LBJFormAssert.assertThat(addNotNullConstraintForm).isFocused();

		LBJTestUtils.setValueOf(addNotNullConstraintForm.getTableNameTextBox(), "LBJ_RELATED_ID");
		LBJTestUtils.setValueOf(addNotNullConstraintForm.getColumnNameTextBox(), "container");
		LBJTestUtils.setValueOf(addNotNullConstraintForm.getDataTypeComboBox(), "integer");
		LBJFormAssert.assertThat(addNotNullConstraintForm).isValid();

		LBJTestUtils.click(addNotNullConstraintForm.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(addNotNullConstraintForm.getWindow(),
				addNotNullConstraintForm);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// only changesets
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// starting from 123
		LBJTestUtils.setValueOf(generateForm.getStartingIdTextBox(), "123");
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
