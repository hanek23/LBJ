package gui.utils;

import java.util.List;

import gui.components.LBJValueHolderComponent;

public class LBJComponentUtils {

	private LBJComponentUtils() {
		// only static methods
	}

	public static boolean areComponentsValid(List<LBJValueHolderComponent<String>> components) {
		boolean areValid = true;
		for (LBJValueHolderComponent<String> component : components) {
			areValid = component.isValid() && areValid;
		}
		return areValid;
	}

}
