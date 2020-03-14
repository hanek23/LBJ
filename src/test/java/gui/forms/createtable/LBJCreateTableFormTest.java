package gui.forms.createtable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.Labels;
import gui.components.LBJMockForm;

public class LBJCreateTableFormTest {

	private LBJCreateTableForm tested;

	@BeforeEach
	public void beforeEach() {
		tested = new LBJCreateTableForm(null, new LBJMockForm());
	}

	@Test
	public void testInitializeComponents() {
		assertThat(tested.getTableNameTextBox().getLabel().getText()).isEqualTo(Labels.TABLE_NAME);
		assertThat(tested.getPrimaryKeyNameTextBox().getLabel().getText())
				.isEqualTo(Labels.CREATE_TABLE_PRIMARY_KEY_NAME);
		assertThat(tested.getPrimaryKeyConstraintNameTextBox().getLabel().getText())
				.isEqualTo(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN);
		assertThat(tested.getDatabasesLabel().getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_DATABASES);
		assertThat(tested.getOracleCheckBox().getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_DATABASES_ORACLE);
		assertThat(tested.getMssqlCheckBox().getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_DATABASES_MSSQL);
		assertThat(tested.getPostgreCheckBox().getLabel().getText())
				.isEqualTo(Labels.CREATE_TABLE_DATABASES_POSTGRESQL);
		assertThat(tested.getSequenceNameTextBox().getLabel().getText()).isEqualTo(Labels.CREATE_TABLE_SEQUENCE_NAME);
		assertThat(tested.toString()).isEqualTo(Labels.TABLE_NAME_FORM);

	}

}
