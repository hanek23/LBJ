package gui.forms;

import com.googlecode.lanterna.gui2.Window;

public abstract class LBJEntityForm<T> extends LBJWizardForm {

	public LBJEntityForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	public abstract T convert();

}