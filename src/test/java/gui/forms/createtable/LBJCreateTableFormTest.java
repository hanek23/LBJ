package gui.forms.createtable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import testutils.LBJTestUtils;

public class LBJCreateTableFormTest {

	@Test
	public void testInitializeComponents() {
		LBJCreateTableForm form = LBJTestUtils.getCreateTableForm();

		assertThat(form.toString()).isEqualTo(Labels.TABLE_FORM);
		assertThat(form.getTableNameTextBox()).hasLabel(Labels.TABLE_NAME);
		assertThat(form.getPrimaryKeyNameTextBox()).hasLabel(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		assertThat(form.getPrimaryKeyConstraintNameTextBox()).hasLabel(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN);
		assertThat(form.getDatabasesLabel()).hasLabel(Labels.CREATE_TABLE_DATABASES);
		assertThat(form.getOracleCheckBox()).hasLabel(Labels.CREATE_TABLE_DATABASES_ORACLE);
		assertThat(form.getMssqlCheckBox()).hasLabel(Labels.CREATE_TABLE_DATABASES_MSSQL);
		assertThat(form.getPostgreCheckBox()).hasLabel(Labels.CREATE_TABLE_DATABASES_POSTGRESQL);
		assertThat(form.getSequenceNameTextBox()).hasLabel(Labels.CREATE_TABLE_SEQUENCE_NAME);
		assertThat(form.getSequenceNameTextBox()).hasLabel(Labels.CREATE_TABLE_SEQUENCE_NAME);

		assertTrue(form.getOracleCheckBox().isChecked());
		assertTrue(form.getMssqlCheckBox().isChecked());
		assertTrue(form.getPostgreCheckBox().isChecked());

		assertThat(form.getTableNameTextBox()).hasCaseValidator(NamingConventions.TABLE_NAME_CASE);
		assertThat(form.getTableNameTextBox()).hasStringRequiredValidator();
		assertThat(form.getTableNameTextBox()).hasLengthValidator();

		assertThat(form.getPrimaryKeyNameTextBox()).hasCaseValidator(NamingConventions.PRIMARY_KEY_NAME_CASE);
		assertThat(form.getPrimaryKeyNameTextBox()).hasStringRequiredValidator();
		assertThat(form.getPrimaryKeyNameTextBox()).hasLengthValidator();

		assertThat(form.getPrimaryKeyConstraintNameTextBox())
				.hasCaseValidator(NamingConventions.PRIMARY_KEY_CONSTRAINT_NAME_CASE);
		assertThat(form.getPrimaryKeyConstraintNameTextBox()).hasStringRequiredValidator();
		assertThat(form.getPrimaryKeyConstraintNameTextBox()).hasLengthValidator();

		assertThat(form.getSequenceNameTextBox()).hasCaseValidator(NamingConventions.SEQUENCE_NAME_CASE);
		assertThat(form.getSequenceNameTextBox()).hasStringRequiredValidator();
		assertThat(form.getSequenceNameTextBox()).hasLengthValidator();

	}

}
