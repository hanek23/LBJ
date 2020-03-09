package gui.components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LBJUpperCaseStringValidatorTest {

	private LBJUpperCaseStringValidator tested = new LBJUpperCaseStringValidator();

	@Test
	public void testIsValidTrue() throws Exception {
		assertTrue(tested.isValid("NBA"));
	}

	@Test
	public void testIsValidFalse() throws Exception {
		assertFalse(tested.isValid("TwoSetViolin"));
	}

	@Test
	public void testIsValidFalse1() {
		assertFalse(tested.isValid("java"));
	}

}
