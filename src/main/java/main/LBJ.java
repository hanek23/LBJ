package main;

import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;

public class LBJ {

	public static void main(String[] args) {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.run();
	}

}
