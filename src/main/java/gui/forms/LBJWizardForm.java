package gui.forms;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import gui.utils.LBJFormUtils;

/**
 * Adds possibility to go back and forth between forms using {@link #backButton}
 * to {@link LBJForm}.
 */
public abstract class LBJWizardForm extends LBJForm {

	private LBJForm previousForm;
	private LBJForm nextForm;

	private Button backButton;

	public LBJWizardForm(Window window, LBJForm previousForm) {
		super(window);
		this.previousForm = previousForm;
		initialize();
	}

	public void initializeBackButton() {
		setBackButton(LBJFormUtils.createBackButtonOn(this, getPreviousForm()));
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

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

}
