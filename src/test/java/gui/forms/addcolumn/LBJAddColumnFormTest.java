package gui.forms.addcolumn;

import static testutils.asserts.LBJFormAssert.assertThat;
import static testutils.asserts.LBJValueHolderComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions;
import gui.suppliers.LBJUpdaterSupplier;
import testutils.LBJTestUtils;

public class LBJAddColumnFormTest {

	@Test
	public void testInitializeComponents() {
		LBJAddColumnForm form = LBJTestUtils.getAddColumnForm();

		// Has all components
		assertThat(form).hasName(Labels.ADD_COLUMN_FORM);
		assertThat(form).hasComponentWithName(Labels.TABLE_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_NAME);
		assertThat(form).hasComponentWithName(Labels.COLUMN_DATA_TYPE);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_NULLABLE);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_INDEX);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_INDEX_NAME);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_FOREIGN_KEY);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_REFERENCED_TABLE);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_REFERENCED_COLUMN);
		assertThat(form).hasComponentWithName(Labels.ADD_COLUMN_FOREIGN_KEY_NAME);

		// in right states
		assertThat(form.getNullableCheckBox()).isNotChecked();
		assertThat(form.getIndexCheckBox()).isNotChecked();
		assertThat(form.getIndexNameTextBox()).isNotEnabled();
		assertThat(form.getForeignKeyCheckBox()).isNotChecked();
		assertThat(form.getReferencedTableNameTextBox()).isNotEnabled();
		assertThat(form.getReferencedColumnNameTextBox()).isNotEnabled();
		assertThat(form.getForeignKeyNameTextBox()).isNotEnabled();

		// with all validators
		assertThat(form.getTableNameTextBox()).isRequired().hasCaseValidator(NamingConventions.TABLE_NAME_CASE)
				.hasLengthValidator();
		assertThat(form.getColumnNameTextBox()).isRequired().hasCaseValidator(NamingConventions.COLUMN_NAME_CASE)
				.hasLengthValidator();
		assertThat(form.getDataTypeTextBox()).isRequired();
		assertThat(form.getIndexNameTextBox()).isRequired().hasCaseValidator(NamingConventions.INDEX_NAME_CASE)
				.hasLengthValidator();
		assertThat(form.getReferencedTableNameTextBox()).isRequired()
				.hasCaseValidator(NamingConventions.TABLE_NAME_CASE).hasLengthValidator();
		assertThat(form.getReferencedColumnNameTextBox()).isRequired()
				.hasCaseValidator(NamingConventions.COLUMN_NAME_CASE).hasLengthValidator();
		assertThat(form.getForeignKeyNameTextBox()).isRequired()
				.hasCaseValidator(NamingConventions.FOREIGN_KEY_NAME_CASE).hasLengthValidator();

		// and updaters
		assertThat(form).hasUpdater(LBJUpdaterSupplier.getAddColumnIndexNameUpdater());
		assertThat(form).hasUpdater(LBJUpdaterSupplier.getAddColumnForeignKeyUpdater());
	}

}
