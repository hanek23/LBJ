package main;

import gui.forms.LBJMainMenuForm;
import gui.suppliers.LBJFormSupplier;
import gui.utils.BeanSupplier;

public class LBJ {

	public static void main(String[] args) {
		BeanSupplier.init();
		LBJMainMenuForm mainMenuForm = LBJFormSupplier.getMainMenuForm();
		mainMenuForm.focus();
		mainMenuForm.startTerminal();
	}

}