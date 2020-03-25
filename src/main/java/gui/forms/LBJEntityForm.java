package gui.forms;

import com.googlecode.lanterna.gui2.Window;

import domain.Entity;

public abstract class LBJEntityForm<T extends Entity> extends LBJWizardForm {

	public LBJEntityForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	public abstract T convert();

}
