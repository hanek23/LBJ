package gui.forms;

import com.googlecode.lanterna.gui2.Window;

public abstract class LBJWizardForm extends LBJForm {

	private LBJForm previousForm;
	private LBJForm nextForm;

	public LBJWizardForm(Window window, LBJForm previousForm) {
		super(window);
		this.previousForm = previousForm;
		initialize();
	}

	public void goToPreviousForm() {
		previousForm.focus();
	}

	public void goToNextForm() {
		nextForm.focus();
	}

	public LBJForm getPreviousForm() {
		return previousForm;
	}

	public void setPreviousForm(LBJForm previousForm) {
		this.previousForm = previousForm;
	}

	public LBJForm getNextForm() {
		return nextForm;
	}

	public void setNextForm(LBJForm nextForm) {
		this.nextForm = nextForm;
	}

}
