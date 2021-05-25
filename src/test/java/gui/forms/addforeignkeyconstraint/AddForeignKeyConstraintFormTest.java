package gui.forms.addforeignkeyconstraint;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import domain.AddForeignKeyConstraint;
import main.LBJ;
import testutils.LBJFormTestCase;
import testutils.LBJTestUtils;

public class AddForeignKeyConstraintFormTest extends LBJFormTestCase {
	private static final String TABLE_NAME = "STEPHEN";
	private static final String COLUMN_NAME = "curry";
	private static final String REFERENCED_TABLE_NAME = "NBA";
	private static final String REFERENCED_COLUMN_NAME = "greatest shooter of all time";
	private static final String FOREIGN_KEY_NAME = NamingConventions.DEFAULT_FOREIGN_KEY_NAME + REFERENCED_TABLE_NAME
			+ NamingConventions.SEPARATOR + REFERENCED_COLUMN_NAME;

	@Test
	void testInitialize() {
		AddForeignKeyConstraintForm form = LBJTestUtils.getAddForeignKeyConstraintForm();

		// Has all components
		assertThat(form).hasName(Labels.ADD_FOREIGN_KEY_CONSTRAINT_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_NAME);
		assertThat(form).hasComponentWithName(Labels.REFERENCED_TABLE);
		assertThat(form).hasComponentWithName(Labels.REFERENCED_COLUMN);
		assertThat(form).hasComponentWithName(Labels.FOREIGN_KEY_NAME);

		// in right states
		assertThat(form.getReferencedTableNameTextBox()).isEnabled();
		assertThat(form.getReferencedColumnNameTextBox()).isEnabled();
		assertThat(form.getForeignKeyNameTextBox()).isEnabled();

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getTableNameCase())
				.hasLengthValidator();
		assertThat(form.getColumnNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getColumnNameCase())
				.hasLengthValidator();
		assertThat(form.getReferencedTableNameTextBox()).isRequired()
				.hasCaseValidator(LBJ.preferences.getTableNameCase()).hasLengthValidator();
		assertThat(form.getReferencedColumnNameTextBox()).isRequired()
				.hasCaseValidator(LBJ.preferences.getColumnNameCase()).hasLengthValidator();
		assertThat(form.getForeignKeyNameTextBox()).isRequired()
				.hasCaseValidator(LBJ.preferences.getForeignKeyNameCase()).hasLengthValidator();

	}

	@Test
	void testConvert() {
		AddForeignKeyConstraintForm form = LBJTestUtils.getAddForeignKeyConstraintForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getColumnNameTextBox(), COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getReferencedTableNameTextBox(), REFERENCED_TABLE_NAME);
		LBJTestUtils.setValueOf(form.getReferencedColumnNameTextBox(), REFERENCED_COLUMN_NAME);
		LBJTestUtils.setValueOf(form.getForeignKeyNameTextBox(), FOREIGN_KEY_NAME);

		AddForeignKeyConstraint column = form.convert();
		assertThat(column.isAddForeignKeyConstraint()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(column.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(column.getName()).isEqualToIgnoringCase(COLUMN_NAME);
		assertThat(column.getForeignKey()).isNotNull();
		assertThat(column.getForeignKey().getReferencedTable()).isEqualToIgnoringCase(REFERENCED_TABLE_NAME);
		assertThat(column.getForeignKey().getReferencedColumn()).isEqualToIgnoringCase(REFERENCED_COLUMN_NAME);
		assertThat(column.getForeignKey().getName()).isEqualToIgnoringCase(FOREIGN_KEY_NAME);
	}

}
