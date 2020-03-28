package endtoend;

import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.forms.removenotnullconstraint.RemoveNotNullConstraintForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Removing not null constraint, whole changelog
 */
public class EndToEndTest3 extends AbstractXmlSupplier implements EndToEndTest {

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
		// whole changelog
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), false);
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
		return true;
	}

}
