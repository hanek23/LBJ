package main;

import gui.forms.LBJMainMenuForm;
import gui.suppliers.LBJFormSupplier;
import gui.utils.BeanSupplier;
import utils.LBJPreferences;
import utils.LBJProperties;

public class LBJ {

	public static final LBJPreferences preferences = new LBJPreferences();
	public static final LBJProperties properties = new LBJProperties();

	public static void main(String[] args) {
		BeanSupplier.init();
		LBJMainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		mainMenuForm.startTerminal();
	}

}