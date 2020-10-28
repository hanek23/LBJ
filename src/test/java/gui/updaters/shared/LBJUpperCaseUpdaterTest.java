package gui.updaters.shared;

import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import constants.Labels;
import constants.NamingConventions.LetterCase;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.utils.LBJCaseUtils;
import testutils.LBJMockForm;
import testutils.LBJTestCase;

class LBJUpperCaseUpdaterTest extends LBJTestCase {

	@Test
	void testUpdate() {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm())
				.addValueUpdater(LBJCaseUtils.caseUpdater(LetterCase.UPPER)).build();

		textBox.setValue("table_name");
		assertThat(textBox).isLowerCase();

		textBox.update();

		assertThat(textBox).isUpperCase();
		textBox.update();
		assertThat(textBox).isUpperCase();
	}

}
