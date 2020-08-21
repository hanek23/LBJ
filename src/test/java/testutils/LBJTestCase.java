package testutils;

import org.junit.jupiter.api.BeforeEach;

import constants.NamingConventions;
import gui.suppliers.LBJFormSupplier;
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
