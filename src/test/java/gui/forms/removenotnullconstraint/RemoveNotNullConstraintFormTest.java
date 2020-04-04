package gui.forms.removenotnullconstraint;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import domain.RemoveNotNullConstraint;
import testutils.LBJTestUtils;

public class RemoveNotNullConstraintFormTest {

	private static final String TABLE_NAME = "ACTION";
	private static final String COLUMN_NAME = "lbj";
	private static final String COLUMN_DATA_TYPE = "integer";

	@Test
	public void testInitialize() {
		RemoveNotNullConstraintForm form = LBJTestUtils.getRemoveNotNullConstraintForm();

		// Has all components
		assertThat(form).hasName(Labels.REMOVE_NOT_NULL_CONSTRAINT_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_DATA_TYPE);

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired().hasCaseValidator(NamingConventions.TABLE_NAME_CASE)
				.hasLengthValidator();
		assertThat(form.getColumnNameTextBox()).isRequired().hasCaseValidator(NamingConventions.COLUMN_NAME_CASE)
				.hasLengthValidator();
		assertThat(form.getDataTypeTextBox()).isRequired();

	}

	@Test
	public void testConvert() {
		RemoveNotNullConstraintForm form = LBJTestUtils.getRemoveNotNullConstraintForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getColumnNameTextBox(), COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getDataTypeTextBox(), COLUMN_DATA_TYPE);

		RemoveNotNullConstraint column = form.convert();
		assertThat(column.isRemoveNotNullConstraint()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(column.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(column.getName()).isEqualToIgnoringCase(COLUMN_NAME);
		assertThat(column.getDataType()).isEqualToIgnoringCase(COLUMN_DATA_TYPE);
	}

}
