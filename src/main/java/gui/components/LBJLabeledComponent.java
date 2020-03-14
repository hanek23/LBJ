package gui.components;

import java.util.List;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Label;

import gui.forms.LBJWizardForm;
import gui.validators.LBJFormValidator;

public abstract class LBJLabeledComponent extends LBJComponent {

	private Label label;
	private List<LBJFormValidator> formValidators;

	@Override
	public boolean isValid() {
		boolean isValid = true;
		for (LBJFormValidator validator : formValidators) {
			isValid = validator.isValid(getForm()) && isValid;
		}
		setLabelColorByValidity(isValid);
		return isValid;
	}

	public LBJLabeledComponent(String name, LBJWizardForm form) {
		super(name, form);
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public void setLabelColorByValidity(boolean isValid) {
		getLabel().setBackgroundColor(isValid ? TextColor.ANSI.DEFAULT : TextColor.ANSI.RED);
	}

}
