package gui.components;

import gui.forms.LBJForm;

public abstract class LBJComponent {

	private String name;
	private boolean enabled;
	private LBJForm form;

	public LBJComponent(String name, LBJForm form) {
		this.name = name;
		this.form = form;
		enabled = true;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LBJForm getForm() {
		return form;
	}

	public void setForm(LBJForm form) {
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
