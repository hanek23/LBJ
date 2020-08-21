package transformers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static testutils.asserts.LBJFormAssert.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Column;
import domain.Entity;
import domain.Table;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.generate.GenerateForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;
import testutils.LBJTestUtils;
import testutils.LBJFormTestCase;

public class FormsToEntityTransformerTest extends LBJFormTestCase {

	@Test
	public void testTransform() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		CreateTableForm createTableForm = mainMenuForm.getCreateTableForm();
		createTableForm.focus();

		LBJTestUtils.setValueOf(createTableForm.getTableNameTextBox(), "TABLE_NAME");
		LBJTestUtils.setValueOf(createTableForm.getPrimaryKeyNameTextBox(), "id_table_name");
		LBJTestUtils.setValueOf(createTableForm.getPrimaryKeyConstraintNameTextBox(), "P_TABLE_NAME");
		LBJTestUtils.setValueOf(createTableForm.getSequenceNameTextBox(), "SEQ_TABLE_NAME");
		assertThat(createTableForm).isValid();
		LBJTestUtils.click(createTableForm.getAddColumnButton());
		AddColumnForm addColumnForm1 = (AddColumnForm) createTableForm.getNextForm();
		assertThat(addColumnForm1).isFocused();

		LBJTestUtils.setValueOf(addColumnForm1.getTableNameTextBox(), "TABLE_NAME");
		LBJTestUtils.setValueOf(addColumnForm1.getColumnNameTextBox(), "name");
		LBJTestUtils.setValueOf(addColumnForm1.getDataTypeTextBox(), "varchar(2)");
		assertThat(addColumnForm1).isValid();
		LBJTestUtils.click(addColumnForm1.getAddColumnButton());
		AddColumnForm addColumnForm2 = (AddColumnForm) addColumnForm1.getNextForm();
		assertThat(addColumnForm2).isFocused();

		LBJTestUtils.setValueOf(addColumnForm2.getTableNameTextBox(), "TABLE_NAME");
		LBJTestUtils.setValueOf(addColumnForm2.getColumnNameTextBox(), "version");
		LBJTestUtils.setValueOf(addColumnForm2.getDataTypeTextBox(), "integer");
		assertThat(addColumnForm2).isValid();
		LBJTestUtils.click(addColumnForm2.getGenerateButton());
		GenerateForm generateForm = LBJFormSupplier.getGenerateForm(addColumnForm2.getWindow(), addColumnForm2);
		assertThat(generateForm).isFocused();

		assertEntities(generateForm);

		generateForm.getOracleCheckBox().unCheck();

		assertEntities(generateForm);

		generateForm.getPostgreCheckBox().unCheck();

		assertEntities(generateForm);

		generateForm.getMssqlCheckBox().unCheck();

		generateForm.getPostgreCheckBox().check();

		assertEntities(generateForm);
	}

	private void assertEntities(GenerateForm generateForm) {
		List<Entity> entities = FormsToEntityTransformer.transform(generateForm);
		Collections.shuffle(entities);
		// 1 table + 2 columns
		assertThat(entities).hasSize(3);
		assertHasTable(entities, "TABLE_NAME", "id_table_name", "P_TABLE_NAME", "SEQ_TABLE_NAME");
		assertHasColumn(entities, "TABLE_NAME", "name", "varchar(2)");
		assertHasColumn(entities, "TABLE_NAME", "version", "integer");
		assertDatabases(entities, generateForm.getOracleCheckBox().isChecked(),
				generateForm.getMssqlCheckBox().isChecked(), generateForm.getPostgreCheckBox().isChecked());
	}

	private void assertDatabases(List<Entity> entities, boolean forOracle, boolean forMssql, boolean forPostgre) {
		for (Entity entity : entities) {
			if (entity.isForOracle() == forOracle && entity.isForMssql() == forMssql
					&& entity.isForPostgre() == forPostgre) {
				continue;
			}
			fail();
		}
	}

	private void assertHasColumn(List<Entity> entities, String tableName, String columnName, String dataType) {
		for (Entity entity : entities) {
			if (!(entity instanceof Column)) {
				continue;
			}
			Column column = (Column) entity;
			if (column.getTableName().equals(tableName) && column.getName().equals(columnName)
					&& column.getDataType().equals(dataType)) {
				// success
				return;
			}
		}
		fail();
	}

	private void assertHasTable(List<Entity> entities, String tableName, String primaryKeyName,
			String primaryKeyConstraintName, String sequenceName) {
		for (Entity entity : entities) {
			if (!(entity instanceof Table)) {
				continue;
			}
			Table table = (Table) entity;
			if (table.getName().equals(tableName) && table.getPrimaryKeyColumnName().equals(primaryKeyName)
					&& table.getPrimaryKeyContrainName().equals(primaryKeyConstraintName)
					&& table.getSequenceName().equals(sequenceName)) {
				// success
				return;
			}
		}
		fail();
	}

}
