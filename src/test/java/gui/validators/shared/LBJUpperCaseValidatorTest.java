package gui.validators.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.TextColor;

import constants.Labels;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJMockForm;
import gui.components.LBJTextBox;
import gui.suppliers.LBJValidatorSupplier;

public class LBJUpperCaseValidatorTest {

	private LBJUpperCaseValidator tested = new LBJUpperCaseValidator();

	@Test
	public void testIsValidTrue() throws Exception {
		assertTrue(tested.isValid("NBA"));
	}

	@Test
	public void testIsValidTrue1() throws Exception {
		assertTrue(tested.isValid("JAVA_1_8_A"));
	}

	@Test
	public void testIsValidFalse() throws Exception {
		assertFalse(tested.isValid("TwoSetViolin"));
	}

	@Test
	public void testIsValidFalse1() {
		assertFalse(tested.isValid("java"));
	}

	@Test
	public void testIsValidFalse2() {
		assertFalse(tested.isValid("Java_1_8_A"));
	}

	@Test
	public void testWithComponentTrue() {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm())
				.addValidator(LBJValidatorSupplier.upperCaseValidator).build();
		textBox.setValue("TABLE_NAME");

		assertTrue(textBox.isValid());
		// null is a default color AKA no color at all
		assertThat(textBox.getLabel().getBackgroundColor()).isEqualTo(null);

	}

	@Test
	public void testWithComponentFalse() {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm())
				.addValidator(LBJValidatorSupplier.upperCaseValidator).build();
		textBox.setValue("TABLE_NAMe");

		assertFalse(textBox.isValid());
		assertThat(textBox.getLabel().getBackgroundColor()).isEqualTo(TextColor.ANSI.RED);
	}

}
