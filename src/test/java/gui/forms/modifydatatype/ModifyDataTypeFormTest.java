package gui.forms.modifydatatype;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import domain.ModifyDataType;
import gui.utils.BeanSupplier;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;
import utils.LBJPreferences;

class ModifyDataTypeFormTest extends LBJFormTestCase {

	private static final String TABLE_NAME = "ACTION";
	private static final String COLUMN_NAME = "lbj";
	private static final String COLUMN_DATA_TYPE = "integer";

	@Test
	void testInitialize() {
		ModifyDataTypeForm form = LBJTestUtils.getModifyDataTypeForm();

		// Has all components
		assertThat(form).hasName(Labels.MODIFY_DATA_TYPE_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_DATA_TYPE);

		// in right states
		assertThat(form.getTableNameTextBox()).isEnabled();
		assertThat(form.getColumnNameTextBox()).isEnabled();
		assertThat(form.getDataTypeTextBox()).isEnabled();

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired()
				.hasCaseValidator(BeanSupplier.get(LBJPreferences.class).getTableNameCase()).hasLengthValidator();
		assertThat(form.getColumnNameTextBox()).isRequired()
				.hasCaseValidator(BeanSupplier.get(LBJPreferences.class).getColumnNameCase()).hasLengthValidator();
		assertThat(form.getDataTypeTextBox()).isRequired();
	}

	@Test
	void testConvert() {
		ModifyDataTypeForm form = LBJTestUtils.getModifyDataTypeForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getColumnNameTextBox(), COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getDataTypeTextBox(), COLUMN_DATA_TYPE);

		ModifyDataType converted = form.convert();
		assertThat(converted.isModifyDataType()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(converted.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(converted.getName()).isEqualToIgnoringCase(COLUMN_NAME);
		assertThat(converted.getDataType()).isEqualToIgnoringCase(COLUMN_DATA_TYPE);
	}

}
