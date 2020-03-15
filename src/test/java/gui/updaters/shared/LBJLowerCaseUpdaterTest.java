package gui.updaters.shared;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions.LetterCase;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJMockForm;
import gui.components.LBJTextBox;
import gui.suppliers.LBJUpdaterSupplier;

public class LBJLowerCaseUpdaterTest {

	@Test
	public void testUpdate() {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm())
				.addUpdater(LBJUpdaterSupplier.caseUpdater(LetterCase.LOWER)).build();
		textBox.setValue("TABLE_NAME");
		assertThat(textBox.getValue()).isUpperCase();

		textBox.update();

		assertThat(textBox.getValue()).isLowerCase();
		textBox.update();
		assertThat(textBox.getValue()).isLowerCase();
	}

}
