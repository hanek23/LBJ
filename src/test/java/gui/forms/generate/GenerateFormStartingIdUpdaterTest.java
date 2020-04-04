package gui.forms.generate;

import static testutils.asserts.LBJValueComponentAssert.assertThat;

import org.junit.jupiter.api.Test;

import gui.components.LBJCheckBox;
import gui.components.LBJTextBox;
import testutils.LBJTestUtils;

public class GenerateFormStartingIdUpdaterTest {

	@Test
	public void testUpdate() {
		GenerateForm form = LBJTestUtils.getGenerateForm();
		LBJCheckBox onlyChangesets = form.getOnlyChangesetsCheckBox();
		LBJTextBox startingId = form.getStartingIdTextBox();

		assertThat(onlyChangesets).isEnabled();
		assertThat(startingId).isNotEnabled();
		assertThat(onlyChangesets).isNotChecked();
		assertThat(startingId).hasValueEqualTo("1");

		LBJTestUtils.setValueOf(onlyChangesets, true);

		assertThat(onlyChangesets).isChecked();
		assertThat(startingId).isEnabled();
		assertThat(startingId).hasValueEqualTo("1");
		startingId.setValue("10");

		LBJTestUtils.setValueOf(onlyChangesets, false);

		assertThat(onlyChangesets).isNotChecked();
		assertThat(startingId).isNotEnabled();
		assertThat(startingId).hasValueEqualTo("1");
	}

}
