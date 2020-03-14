package gui.components;

import gui.forms.LBJWizardForm;

public abstract class LBJComponent {

	private String name;
	private boolean enabled;
	private LBJWizardForm form;

	public LBJComponent(String name, LBJWizardForm form) {
		this.name = name;
		this.form = form;
		form.addComponent(this);
		enabled = true;
	}

	public abstract boolean isValid();

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LBJWizardForm getForm() {
		return form;
	}

	public void setForm(LBJWizardForm form) {
		this.form = form;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " with name: " + name;
	}

}
