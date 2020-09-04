package endtoend;

import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.forms.modifydatatype.ModifyDataTypeForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Modify two columns, only changesets starting from 10
 */
public class EndToEndTestCase12 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = true;

	private static final String TABLE_NAME_1 = "MENDELSSOHN";
	private static final String COLUMN_NAME_1 = "felix";
	private static final String NEW_DATA_TYPE_1 = "awesomeness(9999)";

	private static final String TABLE_NAME_2 = "CHEN";
	private static final String COLUMN_NAME_2 = "ray";
	private static final String NEW_DATA_TYPE_2 = "skill(9999)";

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		ModifyDataTypeForm modifyDataType1 = mainMenuForm.getModifyDataTypeForm();
		modifyDataType1.focus();

		LBJFormAssert.assertThat(modifyDataType1).isFocused();
		LBJTestUtils.click(modifyDataType1.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		modifyDataType1.focus();
		LBJFormAssert.assertThat(modifyDataType1).isFocused();

		// Column withou index nor FK
		LBJTestUtils.setValueOf(modifyDataType1.getTableNameTextBox(), TABLE_NAME_1);
		LBJTestUtils.setValueOf(modifyDataType1.getColumnNameTextBox(), COLUMN_NAME_1);
		LBJTestUtils.setValueOf(modifyDataType1.getDataTypeTextBox(), NEW_DATA_TYPE_1);
		LBJFormAssert.assertThat(modifyDataType1).isValid();
		LBJTestUtils.click(modifyDataType1.getModifyDataTypeButton());

		ModifyDataTypeForm modifyDataType2 = (ModifyDataTypeForm) modifyDataType1.getNextForm();
		LBJTestUtils.setValueOf(modifyDataType2.getTableNameTextBox(), TABLE_NAME_2);
		LBJTestUtils.setValueOf(modifyDataType2.getColumnNameTextBox(), COLUMN_NAME_2);
		LBJTestUtils.setValueOf(modifyDataType2.getDataTypeTextBox(), NEW_DATA_TYPE_2);
		LBJFormAssert.assertThat(modifyDataType2).isValid();

		// Generate
		LBJTestUtils.click(modifyDataType2.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(modifyDataType2.getWindow(), modifyDataType2);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// only changesets
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// starting from 10
		LBJTestUtils.setValueOf(generateForm.getStartingIdTextBox(), "10");
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
