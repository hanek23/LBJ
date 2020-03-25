package main;

import java.io.IOException;

import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;

public class LBJ {

	public static void main(String[] args) throws IOException {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.run();
	}

}
