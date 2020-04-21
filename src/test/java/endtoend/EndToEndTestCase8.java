package endtoend;

import gui.forms.dropnotnullconstraint.DropNotNullConstraintForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Removing not null constraint, for mssql, whole changelog
 */
public class EndToEndTestCase8 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = false;

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		DropNotNullConstraintForm dropNotNullConstraintForm = mainMenuForm.getDropNotNullConstraintForm();
		dropNotNullConstraintForm.focus();

		LBJFormAssert.assertThat(dropNotNullConstraintForm).isFocused();
		LBJTestUtils.click(dropNotNullConstraintForm.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		dropNotNullConstraintForm.focus();
		LBJFormAssert.assertThat(dropNotNullConstraintForm).isFocused();

		LBJTestUtils.setValueOf(dropNotNullConstraintForm.getTableNameTextBox(), "LBJ_RELATED_ID");
		LBJTestUtils.setValueOf(dropNotNullConstraintForm.getColumnNameTextBox(), "container");
		LBJTestUtils.setValueOf(dropNotNullConstraintForm.getDataTypeTextBox(), "integer");
		LBJFormAssert.assertThat(dropNotNullConstraintForm).isValid();

		LBJTestUtils.click(dropNotNullConstraintForm.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(dropNotNullConstraintForm.getWindow(),
				dropNotNullConstraintForm);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// whole changelog
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// only for oracle
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
