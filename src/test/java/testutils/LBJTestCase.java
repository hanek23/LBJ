package testutils;

import org.junit.jupiter.api.BeforeEach;

import gui.utils.BeanSupplier;

/**
 * Initializes all beans
 */
public abstract class LBJTestCase {

	@BeforeEach
	public void beforeEach() {
		BeanSupplier.init();
	}

}
