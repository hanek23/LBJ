package gui.updaters.shared;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import testutils.LBJMockForm;

class LBJRequiredLabelComponentUpdaterTest {

	@Test
	void testUpdate() {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm()).required().build();

		textBox.update();
		assertThat(textBox.getLabel().getText()).isEqualTo(Labels.TABLE_NAME + "*");
		textBox.update();
		assertThat(textBox.getLabel().getText()).isEqualTo(Labels.TABLE_NAME + "*");

		textBox.setEnabled(false);

		textBox.update();
		assertThat(textBox.getLabel().getText()).isEqualTo(Labels.TABLE_NAME);
		textBox.update();
		assertThat(textBox.getLabel().getText()).isEqualTo(Labels.TABLE_NAME);
	}

}
