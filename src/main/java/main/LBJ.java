package main;

import java.util.prefs.Preferences;

import gui.forms.LBJMainMenuForm;
import gui.suppliers.LBJFormSupplier;

public class LBJ {

	public static final Preferences preferences = Preferences.userRoot().node(LBJ.class.getSimpleName());

	public static void main(String[] args) {
		LBJMainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		mainMenuForm.startTerminal();
	}

}