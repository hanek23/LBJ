package gui.forms.createtable;

import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import testutils.LBJTestUtils;

public class CreateTableFormTest {

	@Test
	public void testInitializeComponents() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();

		assertThat(form).hasName(Labels.TABLE_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_DATABASES);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_DATABASES_ORACLE);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_DATABASES_MSSQL);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_DATABASES_POSTGRESQL);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_SEQUENCE_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_SEQUENCE_NAME);

		assertThat(form.getOracleCheckBox()).isChecked();
		assertThat(form.getMssqlCheckBox()).isChecked();
		assertThat(form.getPostgreCheckBox()).isChecked();

		assertThat(form.getTableNameTextBox()).hasCaseValidator(NamingConventions.TABLE_NAME_CASE);
		assertThat(form.getTableNameTextBox()).isRequired();
		assertThat(form.getTableNameTextBox()).hasLengthValidator();

		assertThat(form.getPrimaryKeyNameTextBox()).hasCaseValidator(NamingConventions.PRIMARY_KEY_NAME_CASE);
		assertThat(form.getPrimaryKeyNameTextBox()).isRequired();
		assertThat(form.getPrimaryKeyNameTextBox()).hasLengthValidator();

		assertThat(form.getPrimaryKeyConstraintNameTextBox())
				.hasCaseValidator(NamingConventions.PRIMARY_KEY_CONSTRAINT_NAME_CASE);
		assertThat(form.getPrimaryKeyConstraintNameTextBox()).isRequired();
		assertThat(form.getPrimaryKeyConstraintNameTextBox()).hasLengthValidator();

		assertThat(form.getSequenceNameTextBox()).hasCaseValidator(NamingConventions.SEQUENCE_NAME_CASE);
		assertThat(form.getSequenceNameTextBox()).isRequired();
		assertThat(form.getSequenceNameTextBox()).hasLengthValidator();

	}

}
