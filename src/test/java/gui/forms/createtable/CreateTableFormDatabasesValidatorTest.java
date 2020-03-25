package gui.forms.createtable;

import static testutils.asserts.LBJLabeledComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import gui.components.LBJPlainLabel;
import testutils.LBJTestUtils;

public class CreateTableFormDatabasesValidatorTest {

	@Test
	public void testIsValid() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();
		form.focus();
		LBJPlainLabel databases = form.getDatabasesLabel();
		assertThat(databases).isValid();

		form.getOracleCheckBox().unCheck();
		// still good
		assertThat(databases).isValid();

		form.getPostgreCheckBox().unCheck();
		// still good
		assertThat(databases).isValid();

		form.getMssqlCheckBox().unCheck();
		// atleast one checkbox must be enabled so component should not be valid
		assertThat(databases).isNotValid();

		form.getOracleCheckBox().check();
		// good again
		assertThat(databases).isValid();
	}

}
