package endtoend;

import gui.forms.addcolumn.AddColumnForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * One column with index and foreign key, for all databases, whole changelog
 */
public class EndToEndTestCase1 extends AbstractXmlSupplier implements EndToEndTestCase {

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

		// Column
		LBJTestUtils.setValueOf(addColumnForm.getTableNameTextBox(), "LBJ_REFERENCE");
		LBJTestUtils.setValueOf(addColumnForm.getColumnNameTextBox(), "action");
		LBJTestUtils.setValueOf(addColumnForm.getDataTypeTextBox(), "integer");
		// nullable
		LBJTestUtils.setValueOf(addColumnForm.getNullableCheckBox(), true);
		// with index
		LBJTestUtils.setValueOf(addColumnForm.getIndexCheckBox(), true);
		LBJTestUtils.setValueOf(addColumnForm.getIndexNameTextBox(), "I_LBJ_REFERENCE_NACTION");
		// and foreign key
		LBJTestUtils.setValueOf(addColumnForm.getForeignKeyCheckBox(), true);
		LBJTestUtils.setValueOf(addColumnForm.getReferencedTableNameTextBox(), "LBJ_ACTION");
		LBJTestUtils.setValueOf(addColumnForm.getReferencedColumnNameTextBox(), "id_lbj_action");
		LBJTestUtils.setValueOf(addColumnForm.getForeignKeyNameTextBox(), "F_LBJ_REF_ID_LBJ_ACTION");

		LBJFormAssert.assertThat(addColumnForm).isValid();
		LBJTestUtils.click(addColumnForm.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(addColumnForm.getWindow(), addColumnForm);
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
