package gui.forms.createtable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import constants.NamingConventions;
import gui.LBJMockWindow;
import gui.components.LBJCheckBox;
import gui.components.LBJMockForm;
import gui.components.LBJTextBox;

public class LBJCreateTableFormSequenceNameUpdaterTest {

	private static final String TABLE_NAME = "TABLE_NAME";

	@Test
	public void testUpdate() {
		LBJCreateTableForm form = new LBJCreateTableForm(new LBJMockWindow(), new LBJMockForm());
		form.focus();

		LBJTextBox tableName = form.getTableNameTextBox();
		LBJCheckBox oracle = form.getOracleCheckBox();
		LBJCheckBox postgre = form.getPostgreCheckBox();
		LBJTextBox sequenceName = form.getSequenceNameTextBox();

		assertTrue(sequenceName.isEnabled());
		assertThat(sequenceName.getValue()).isBlank();
		assertTrue(oracle.isChecked());
		assertTrue(postgre.isChecked());

		tableName.setValue(TABLE_NAME);
		form.update();

		// table name is not focused so nothing should have updated
		assertThat(sequenceName.getValue()).isBlank();

		tableName.getTextBox().onEnterFocus(null, null);
		form.update();

		assertThat(sequenceName.getValue())
				.isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME);
		oracle.getCheckBox().setChecked(false);
		form.update();

		assertThat(sequenceName.getValue())
				.isEqualToIgnoringCase(NamingConventions.SEQUENCE_NAME_DEFAULT_VALUE + TABLE_NAME);
		postgre.getCheckBox().setChecked(false);
		form.update();

		// If Oracle and Postgre are not checked sequence name should be disabled
		assertFalse(sequenceName.isEnabled());

		oracle.getCheckBox().setChecked(true);
		form.update();
		assertTrue(sequenceName.isEnabled());
	}

}
