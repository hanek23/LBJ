package testutils;

import org.junit.jupiter.api.BeforeEach;

import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;

/**
 * Resets instance of {@link MainMenuForm} before each test.
 */
public abstract class MainMenuTest {

	@BeforeEach
	public void beforeEach() {
		LBJFormSupplier.resetMainMenu();
	}

}
