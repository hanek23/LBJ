package gui.forms.createtable;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import domain.CreateTable;
import gui.utils.BeanSupplier;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;
import utils.LBJPreferences;

public class CreateTableFormTest extends LBJFormTestCase {

	private static final String TABLE_NAME = "ACTION";
	private static final String PRIMARY_KEY_NAME = "id";
	private static final String PRIMARY_KEY_CONSTRAINT_NAME = NamingConventions.DEFAULT_PRIMARY_KEY_CONSTRAINT_NAME
			+ TABLE_NAME;
	private static final String SEQUENCE_NAME = NamingConventions.DEFAULT_SEQUENCE_NAME + TABLE_NAME;

	@Test
	public void testInitialize() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();

		assertThat(form).hasName(Labels.CREATE_TABLE_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		assertThat(form).hasComponentWithName(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN_NAME);
		assertThat(form).hasComponentWithName(Labels.TABLE_SEQUENCE_NAME);

		assertThat(form.getTableNameTextBox()).isRequired().hasLengthValidator()
				.hasCaseValidator(BeanSupplier.get(LBJPreferences.class).getTableNameCase());

		assertThat(form.getPrimaryKeyNameTextBox()).isRequired().hasLengthValidator()
				.hasCaseValidator(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyNameCase());

		assertThat(form.getPrimaryKeyConstraintNameTextBox()).isRequired().hasLengthValidator()
				.hasCaseValidator(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyConstraintNameCase());

		assertThat(form.getSequenceNameTextBox()).isRequired().hasLengthValidator()
				.hasCaseValidator(BeanSupplier.get(LBJPreferences.class).getSequenceNameCase());

	}

	@Test
	public void testConvert() {
		CreateTableForm form = LBJTestUtils.getCreateTableForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getPrimaryKeyNameTextBox(), PRIMARY_KEY_NAME);
		LBJTestUtils.setValueOf(form.getPrimaryKeyConstraintNameTextBox(), PRIMARY_KEY_CONSTRAINT_NAME);
		LBJTestUtils.setValueOf(form.getSequenceNameTextBox(), SEQUENCE_NAME);

		CreateTable table = form.convert();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(table.getName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(table.getPrimaryKeyColumnName()).isEqualToIgnoringCase(PRIMARY_KEY_NAME);
		assertThat(table.getPrimaryKeyContrainName()).isEqualToIgnoringCase(PRIMARY_KEY_CONSTRAINT_NAME);
		assertThat(table.getSequenceName()).isEqualToIgnoringCase(SEQUENCE_NAME);
	}

}
