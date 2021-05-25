package endtoend;

import static org.assertj.core.api.Assertions.assertThat;
import gui.forms.addforeignkeyconstraint.AddForeignKeyConstraintForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Add two foreign key contraints, only changesets, starting ID = 30
 */
public class EndToEndTestCase16 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = true;

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		AddForeignKeyConstraintForm addFKeyCForm1 = mainMenuForm.getAddForeignKeyConstraintForm();
		addFKeyCForm1.focus();

		// SET F KEY C 1
		LBJFormAssert.assertThat(addFKeyCForm1).isFocused();
		LBJTestUtils.click(addFKeyCForm1.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		addFKeyCForm1.focus();
		LBJFormAssert.assertThat(addFKeyCForm1).isFocused();

		LBJTestUtils.setValueOf(addFKeyCForm1.getTableNameTextBox(), "TABLE_1");
		LBJTestUtils.setValueOf(addFKeyCForm1.getColumnNameTextBox(), "COLUMN_1");
		LBJTestUtils.setValueOf(addFKeyCForm1.getReferencedTableNameTextBox(), "REF_TABLE_1");
		LBJTestUtils.setValueOf(addFKeyCForm1.getReferencedColumnNameTextBox(), "REF_COLUMN_1");
		assertThat(addFKeyCForm1.getForeignKeyNameTextBox().getValue()).isEqualTo("F_TABLE_1_REF_TABLE_1_COLUMN_1");
		LBJFormAssert.assertThat(addFKeyCForm1).isValid();

		// SET F KEY C 2
		LBJTestUtils.click(addFKeyCForm1.getAddAnotherForeignKeyConstraintButton());
		AddForeignKeyConstraintForm addFKeyCForm2 = (AddForeignKeyConstraintForm) addFKeyCForm1.getNextForm();

		// going back and forth
		LBJFormAssert.assertThat(addFKeyCForm2).isFocused();
		LBJTestUtils.click(addFKeyCForm2.getBackButton());
		LBJFormAssert.assertThat(addFKeyCForm1).isFocused();
		LBJTestUtils.click(addFKeyCForm1.getAddAnotherForeignKeyConstraintButton());
		LBJFormAssert.assertThat(addFKeyCForm2).isFocused();

		// SET F KEY C 1
		LBJTestUtils.setValueOf(addFKeyCForm2.getTableNameTextBox(), "TABLE_2");
		LBJTestUtils.setValueOf(addFKeyCForm2.getColumnNameTextBox(), "COLUMN_2");
		LBJTestUtils.setValueOf(addFKeyCForm2.getReferencedTableNameTextBox(), "REF_TABLE_2");
		LBJTestUtils.setValueOf(addFKeyCForm2.getReferencedColumnNameTextBox(), "REF_COLUMN_2");
		assertThat(addFKeyCForm2.getForeignKeyNameTextBox().getValue()).isEqualTo("F_TABLE_2_REF_TABLE_2_COLUMN_2");
		LBJFormAssert.assertThat(addFKeyCForm2).isValid();

		// GENERATE FORM
		LBJTestUtils.click(addFKeyCForm2.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(addFKeyCForm2.getWindow(), addFKeyCForm2);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// only changesets
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
		// starting from 30
		LBJTestUtils.setValueOf(generateForm.getStartingIdTextBox(), "30");
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
