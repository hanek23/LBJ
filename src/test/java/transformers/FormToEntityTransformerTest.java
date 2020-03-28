package transformers;

import org.junit.jupiter.api.Test;

import gui.forms.createtable.CreateTableForm;
import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;

public class FormToEntityTransformerTest {

	@Test
	public void testTransform() {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		CreateTableForm createTableForm = mainMenuForm.getCreateTableForm();
	}

}
