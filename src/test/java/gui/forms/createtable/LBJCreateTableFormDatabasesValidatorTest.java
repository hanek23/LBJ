package gui.forms.createtable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.TextColor;

import gui.LBJMockWindow;
import gui.components.LBJMockForm;
import gui.components.LBJPlainLabel;

public class LBJCreateTableFormDatabasesValidatorTest {

	@Test
	public void testIsValid() {
		LBJCreateTableForm form = new LBJCreateTableForm(new LBJMockWindow(), new LBJMockForm());
		form.focus();
		LBJPlainLabel databases = form.getDatabasesLabel();
		// null is default color
		assertThat(databases.getLabel().getBackgroundColor()).isEqualTo(null);

		form.getOracleCheckBox().getCheckBox().setChecked(false);
		form.validate();
		// still good
		assertThat(databases.getLabel().getBackgroundColor()).isEqualTo(null);

		form.getPostgreCheckBox().getCheckBox().setChecked(false);
		form.validate();
		// still good
		assertThat(databases.getLabel().getBackgroundColor()).isEqualTo(null);

		form.getMssqlCheckBox().getCheckBox().setChecked(false);
		form.validate();
		// atleast one checkbox must be enabled so this is not valid
		assertThat(databases.getLabel().getBackgroundColor()).isEqualTo(TextColor.ANSI.RED);

		form.getOracleCheckBox().getCheckBox().setChecked(true);
		form.validate();
		// good again
		assertThat(databases.getLabel().getBackgroundColor()).isEqualTo(null);
	}

}
