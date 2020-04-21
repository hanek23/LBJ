package main;

import java.util.prefs.Preferences;

import gui.forms.mainmenu.MainMenuForm;
import gui.suppliers.LBJFormSupplier;

public class LBJ {

	public static final Preferences preferences = Preferences.userRoot().node(LBJ.class.getSimpleName());

	public static void main(String[] args) {
		MainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		mainMenuForm.startTerminal();
	}

}