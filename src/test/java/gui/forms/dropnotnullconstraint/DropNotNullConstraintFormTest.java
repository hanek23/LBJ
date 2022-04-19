package gui.forms.dropnotnullconstraint;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import domain.DropNotNullConstraint;
import main.LBJ;
import testutils.LBJTestCase;
import testutils.LBJTestUtils;

public class DropNotNullConstraintFormTest extends LBJTestCase {

	private static final String TABLE_NAME = "ACTION";
	private static final String COLUMN_NAME = "lbj";
	private static final String COLUMN_DATA_TYPE = "integer";

	@Test
	public void testInitialize() {
		DropNotNullConstraintForm form = LBJTestUtils.getDropNotNullConstraintForm();

		// Has all components
		assertThat(form).hasName(Labels.DROP_NOT_NULL_CONSTRAINT_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_NAME);
		assertThat(form).hasComponentWithName(Labels.DATA_TYPE);

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getTableNameCase())
				.hasLengthValidator();
		assertThat(form.getColumnNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getColumnNameCase())
				.hasLengthValidator();
		assertThat(form.getDataTypeComboBox()).hasCaseValidator(LBJ.preferences.getDataTypeCase()).isRequired();
	}

	@Test
	public void testConvert() {
		DropNotNullConstraintForm form = LBJTestUtils.getDropNotNullConstraintForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getColumnNameTextBox(), COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getDataTypeComboBox(), COLUMN_DATA_TYPE);

		DropNotNullConstraint column = form.convert();
		assertThat(column.isDropNotNullConstraint()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(column.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(column.getName()).isEqualToIgnoringCase(COLUMN_NAME);
		assertThat(column.getDataType()).isEqualToIgnoringCase(COLUMN_DATA_TYPE);
	}

}
