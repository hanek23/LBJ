package endtoend;

import static org.assertj.core.api.Assertions.assertThat;

import gui.forms.dropforeignkeyconstraint.DropForeignKeyConstraintForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.AbstractXmlSupplier;
import testutils.LBJTestUtils;
import testutils.asserts.LBJFormAssert;

/**
 * Add two foreign key contraints, full changelog
 */
public class EndToEndTestCase17 extends AbstractXmlSupplier implements EndToEndTestCase {

	private static final boolean ONLY_CHANGESETS = false;

	@Override
	public void test() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		DropForeignKeyConstraintForm dropFKeyCForm1 = mainMenuForm.getDropForeignKeyConstraintForm();
		dropFKeyCForm1.focus();

		// SET F KEY C 1
		LBJFormAssert.assertThat(dropFKeyCForm1).isFocused();
		LBJTestUtils.click(dropFKeyCForm1.getBackButton());
		LBJFormAssert.assertThat(mainMenuForm).isFocused();
		dropFKeyCForm1.focus();
		LBJFormAssert.assertThat(dropFKeyCForm1).isFocused();

		LBJTestUtils.setValueOf(dropFKeyCForm1.getTableNameTextBox(), "TABLE_1");
		assertThat(dropFKeyCForm1.getForeignKeyNameTextBox().getValue()).isBlank();
		LBJTestUtils.setValueOf(dropFKeyCForm1.getForeignKeyNameTextBox(), "F_TABLE_1_TABLE_1");
		LBJFormAssert.assertThat(dropFKeyCForm1).isValid();

		// SET F KEY C 2
		LBJTestUtils.click(dropFKeyCForm1.getDropAnotherForeignKeyConstraintButton());
		DropForeignKeyConstraintForm addFKeyCForm2 = (DropForeignKeyConstraintForm) dropFKeyCForm1.getNextForm();

		// going back and forth
		LBJFormAssert.assertThat(addFKeyCForm2).isFocused();
		LBJTestUtils.click(addFKeyCForm2.getBackButton());
		LBJFormAssert.assertThat(dropFKeyCForm1).isFocused();
		LBJTestUtils.click(dropFKeyCForm1.getDropAnotherForeignKeyConstraintButton());
		LBJFormAssert.assertThat(addFKeyCForm2).isFocused();

		// SET F KEY C 2
		LBJTestUtils.setValueOf(addFKeyCForm2.getTableNameTextBox(), "TABLE_2");
		assertThat(addFKeyCForm2.getForeignKeyNameTextBox().getValue()).isBlank();
		LBJTestUtils.setValueOf(addFKeyCForm2.getForeignKeyNameTextBox(), "F_TOTALY_WRONG_NAME");
		LBJFormAssert.assertThat(addFKeyCForm2).isValid();

		// GENERATE FORM
		LBJTestUtils.click(addFKeyCForm2.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(addFKeyCForm2.getWindow(), addFKeyCForm2);
		LBJFormAssert.assertThat(generateForm).isFocused();

		LBJTestUtils.setValueOf(generateForm.getAuthorTextBox(), "hanek23");
		// full change log
		LBJTestUtils.setValueOf(generateForm.getOnlyChangesetsCheckBox(), ONLY_CHANGESETS);
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
