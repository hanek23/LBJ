package gui.updaters.shared;

import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions.LetterCase;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.suppliers.LBJUpdaterSupplier;
import testutils.LBJMockForm;

public class LBJUpperCaseUpdaterTest {

	@Test
	public void testUpdate() {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm())
				.addValueUpdater(LBJUpdaterSupplier.caseUpdater(LetterCase.UPPER)).build();

		textBox.setValue("table_name");
		assertThat(textBox).isLowerCase();

		textBox.update();

		assertThat(textBox).isUpperCase();
		textBox.update();
		assertThat(textBox).isUpperCase();
	}

}
