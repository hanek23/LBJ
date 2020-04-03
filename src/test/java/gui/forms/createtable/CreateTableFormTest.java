package gui.forms.createtable;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import domain.Table;
import testutils.LBJTestUtils;

public class CreateTableFormTest {

	private static final String TABLE_NAME = "ACTION";
	private static final String PRIMARY_KEY_NAME = "id";
	private static final String PRIMARY_KEY_CONSTRAINT_NAME = NamingConventions.PRIMARY_KEY_CONSTRAINT
			+ TABLE_NAME;
	private static final String SEQUENCE_NAME = NamingConventions.SEQUENCE_NAME + TABLE_NAME;

	@Test
	public void testInitialize() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();

		assertThat(form).hasName(Labels.TABLE_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_SEQUENCE_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_SEQUENCE_NAME);

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

	@Test
	public void testConvert() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getPrimaryKeyNameTextBox(), PRIMARY_KEY_NAME);
		LBJTestUtils.setValueOf(form.getPrimaryKeyConstraintNameTextBox(), PRIMARY_KEY_CONSTRAINT_NAME);
		LBJTestUtils.setValueOf(form.getSequenceNameTextBox(), SEQUENCE_NAME);

		Table table = form.convert();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(table.getName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(table.getPrimaryKeyColumnName()).isEqualToIgnoringCase(PRIMARY_KEY_NAME);
		assertThat(table.getPrimaryKeyContrainName()).isEqualToIgnoringCase(PRIMARY_KEY_CONSTRAINT_NAME);
		assertThat(table.getSequenceName()).isEqualToIgnoringCase(SEQUENCE_NAME);
	}

}
