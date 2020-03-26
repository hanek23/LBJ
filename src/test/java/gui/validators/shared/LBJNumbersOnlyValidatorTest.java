package gui.validators.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import gui.suppliers.LBJValidatorSupplier;

public class LBJNumbersOnlyValidatorTest {

	@ParameterizedTest
	@MethodSource("argumentSource")
	public void testIsValid(String number, boolean expected) {
		assertEquals(expected, LBJValidatorSupplier.getNumbersOnlyValidator().isValid(number));
	}

	private static Stream<Arguments> argumentSource() {
		return Stream.of(
				Arguments.of(null, false),
				Arguments.of("", false),
				Arguments.of("  ", false),
				Arguments.of("asd", false),
				Arguments.of("1a", false),
				Arguments.of("1-", false),
				Arguments.of("-5", false),
				Arguments.of("23", true));
	}
}
