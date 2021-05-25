package gui.forms.dropforeignkeyconstraint;

import static org.assertj.core.api.Assertions.assertThat;
import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import domain.DropForeignKeyConstraint;
import main.LBJ;
import testutils.LBJTestCase;
import testutils.LBJTestUtils;

public class DropForeignKeyConstraintFormTest extends LBJTestCase {

	private static final String TABLE_NAME = "SCOTTIE PIPPEN";
	private static final String REFERENCED_TABLE_NAME = "NBA";
	private static final String REFERENCED_COLUMN_NAME = "33";
	private static final String FOREIGN_KEY_NAME = NamingConventions.DEFAULT_FOREIGN_KEY_NAME + REFERENCED_TABLE_NAME
			+ NamingConventions.SEPARATOR + REFERENCED_COLUMN_NAME;

	@Test
	void testInitialize() {
		DropForeignKeyConstraintForm form = LBJTestUtils.getDropForeignKeyConstraintForm();

		// Has all components
		assertThat(form).hasName(Labels.DROP_FOREIGN_KEY_CONSTRAINT_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.FOREIGN_KEY_NAME);

		// in right states
		assertThat(form.getForeignKeyNameTextBox()).isEnabled();

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired().hasCaseValidator(LBJ.preferences.getTableNameCase())
				.hasLengthValidator();
		assertThat(form.getForeignKeyNameTextBox()).isRequired()
				.hasCaseValidator(LBJ.preferences.getForeignKeyNameCase()).hasLengthValidator();

	}

	@Test
	void testConvert() {
		DropForeignKeyConstraintForm form = LBJTestUtils.getDropForeignKeyConstraintForm();

		LBJTestUtils.setValueOf(form.getTableNameTextBox(), TABLE_NAME);
		LBJTestUtils.setValueOf(form.getForeignKeyNameTextBox(), FOREIGN_KEY_NAME);

		DropForeignKeyConstraint column = form.convert();
		assertThat(column.isDropForeignKeyConstraint()).isTrue();
		// ignoring case because testing case upadaters is not the goal of this test
		assertThat(column.getTableName()).isEqualToIgnoringCase(TABLE_NAME);
		assertThat(column.getForeignKey()).isNotNull();
		assertThat(column.getForeignKey().getName()).isEqualToIgnoringCase(FOREIGN_KEY_NAME);
	}

}
