package main;

import java.io.IOException;

import gui.forms.mainmenu.LBJMainMenuForm;
import gui.suppliers.LBJFormSupplier;

public class LBJ {

	public static void main(String[] args) throws IOException {
		LBJMainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.run();
		// MainMenuForm mainMenuForm = new MainMenuForm();
		// mainMenuForm.run();
	}

}
