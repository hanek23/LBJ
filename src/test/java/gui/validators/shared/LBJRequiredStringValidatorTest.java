package gui.validators.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.googlecode.lanterna.TextColor;

import constants.Labels;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJMockForm;
import gui.components.LBJTextBox;
import gui.validators.LBJValidatorSupplier;

public class LBJRequiredStringValidatorTest {

	private LBJRequiredStringValidator tested = new LBJRequiredStringValidator();

	@Test
	public void testIsValidTrue() {
		assertTrue(tested.isValid("LBJ is the GOAT"));
	}

	@Test
	public void testIsValidNull() {
		assertFalse(tested.isValid(null));
	}

	@Test
	public void testIsValidEmpty() {
		assertFalse(tested.isValid(""));
	}

	@Test
	public void testIsValidWhitespace() {
		assertFalse(tested.isValid(" "));
	}

	@Test
	public void testWithComponentTrue() {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm())
				.addValidator(LBJValidatorSupplier.stringRequiredValidator).build();
		textBox.setValue("TABLE_NAME");

		assertTrue(textBox.isValid());
		// null is a default color AKA no color at all
		assertThat(textBox.getLabel().getBackgroundColor()).isEqualTo(null);

	}

	@ParameterizedTest
	@ValueSource(strings = { "", " " })
	public void testWithComponentFalse(String invalidValue) {
		LBJTextBox textBox = new LBJTextBoxBuilder(Labels.TABLE_NAME, new LBJMockForm())
				.addValidator(LBJValidatorSupplier.stringRequiredValidator).build();

		textBox.setValue(invalidValue);

		assertFalse(textBox.isValid());
		assertThat(textBox.getLabel().getBackgroundColor()).isEqualTo(TextColor.ANSI.RED);
	}

}
