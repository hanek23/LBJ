package gui.components;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.gui2.Label;

import gui.forms.LBJForm;
import gui.updaters.LBJComponentUpdater;
import gui.updaters.shared.LBJActivatorComponentUpdater;

/**
 * Abstract base for all {@link LBJComponent}s like {@link LBJLabeledComponent}
 * and {@link LBJValueComponent}. It has 'name' which is in most cases
 * (component must extends {@link LBJLabeledComponent}) equal to {@link Label}'s
 * text.
 */
public abstract class LBJComponent {

	private String name;
	private boolean enabled;
	private LBJForm form;
	private LBJValueComponent<Boolean> activatorComponent;
	private List<LBJComponentUpdater> updaters;

	public LBJComponent(String name, LBJForm form) {
		this.name = name;
		this.form = form;
		form.addComponent(this);
		enabled = true;
	}

	/**
	 * Iterates throught all updaters associated with this component and updates it
	 */
	public void update() {
		for (LBJComponentUpdater updater : getUpdaters()) {
			updater.update(this);
		}
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

	/**
	 * Default implementation of {@link LBJValueHolderComponent<Boolean>} is
	 * {@link LBJCheckBox} so most of the time this is used inside
	 * {@link LBJActivatorComponentUpdater} as a way of activating this component by
	 * the state of another one
	 */
	public LBJValueComponent<Boolean> getActivatorComponent() {
		return activatorComponent;
	}

	/**
	 * Default implementation of {@link LBJValueHolderComponent<Boolean>} is
	 * {@link LBJCheckBox} so most of the time this is used inside {@link } as a way
	 * of activating this component by the state of another one
	 */
	public void setActivatorComponent(LBJValueComponent<Boolean> activatorComponent) {
		this.activatorComponent = activatorComponent;
	}

	public boolean isActivatorComponentFocused() {
		return activatorComponent != null && activatorComponent.isFocused();
	}

	public List<LBJComponentUpdater> getUpdaters() {
		if (updaters == null) {
			updaters = new ArrayList<>();
		}
		return updaters;
	}

	public boolean addUpdater(LBJComponentUpdater updater) {
		return getUpdaters().add(updater);
	}

}
