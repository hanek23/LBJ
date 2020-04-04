package gui.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;

import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJComponent;
import gui.components.LBJValueComponent;
import gui.updaters.LBJFormUpdater;
import gui.updaters.LBJValueUpdater;
import gui.utils.LBJFormUtils;
import gui.validators.LBJFormValidator;
import gui.validators.LBJValueValidator;
import main.LBJ;

/**
 * Abstract base for all forms in {@link LBJ} application. It serves as shared
 * place for {@link LBJFormUpdater} and {@link LBJFormValidator} to execute. It
 * keeps information about all {@link LBJComponent}s present form so that
 * {@link #validate()} and {@link #update()} methods can execute
 * {@link LBJFormValidator}/{@link LBJFormUpdater} as well as any
 * {@link LBJValueValidator}/{@link LBJValueUpdater} each component might have.
 */
public abstract class LBJForm implements Runnable {

	private Window window;
	private boolean initialized;
	private Panel content;
	private List<LBJComponent> components;
	private List<LBJFormUpdater<LBJForm>> updaters;
	private List<LBJFormValidator<LBJForm>> validators;

	public LBJForm(Window window) {
		this.window = window;
	}

	@Override
	public abstract String toString();

	public void initialize() {
		initializeContent();
		initializeComponents();
		initializeButtons();
		addFormUpdaters();
		addFormValidators();
		addComponents();
		addButtons();
		initialized = true;
	}

	public void initializeContent() {
		setContent(LBJFormUtils.initializeDefaultContent());
	}

	/**
	 * Inside this method you should initialize your components using for example
	 * {@link LBJTextBoxBuilder} or {@link LBJCheckBoxBuilder}
	 */
	public abstract void initializeComponents();

	/**
	 * Inside this method you should initialize {@link Button} as well as their
	 * {@link Runnable}
	 */
	public abstract void initializeButtons();

	/**
	 * Here you can add special {@link LBJFormUpdater} that will operate on this
	 * form
	 */
	public abstract void addFormUpdaters();

	/**
	 * Here you can add special {@link LBJFormValidator} that will operate on this
	 * form
	 */
	public abstract void addFormValidators();

	/**
	 * Using {@link LBJFormUtils} you can add your components to this form so that
	 * they can be seen.
	 */
	public abstract void addComponents();

	/**
	 * Using {@link LBJFormUtils} you can add your buttons to this form so that they
	 * can be seen.
	 */
	public abstract void addButtons();

	@Override
	public void run() {
		focus();
	}

	public void update() {
		if (!isFocused()) {
			return;
		}
		for (LBJFormUpdater<LBJForm> updater : getUpdaters()) {
			updater.update(this);
		}
		for (LBJComponent component : getComponents()) {
			component.update();
		}
	}

	public boolean validate() {
		if (!isFocused() || !isInitialized()) {
			return true;
		}
		// one last update before validating
		update();
		boolean isFormValid = true;
		for (LBJFormValidator<LBJForm> validator : getValidators()) {
			isFormValid = validator.isValid(this) && isFormValid;
		}
		for (LBJComponent component : getComponents()) {
			if (component instanceof LBJValueComponent<?>) {
				isFormValid = ((LBJValueComponent<?>) component).isValid() && isFormValid;
			}
		}
		return isFormValid;
	}

	public void focus() {
		window.setComponent(content);
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public boolean isFocused() {
		return Objects.equals(window.getComponent(), content);
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public Panel getContent() {
		return content;
	}

	public void setContent(Panel content) {
		this.content = content;
	}

	public List<LBJComponent> getComponents() {
		if (components == null) {
			components = new ArrayList<>();
		}
		return components;
	}

	public boolean addComponent(LBJComponent component) {
		return getComponents().add(component);
	}

	public List<LBJFormUpdater<LBJForm>> getUpdaters() {
		if (updaters == null) {
			updaters = new ArrayList<>();
		}
		return updaters;
	}

	@SuppressWarnings("unchecked")
	public boolean addUpdater(LBJFormUpdater<? extends LBJForm> updater) {
		return getUpdaters().add((LBJFormUpdater<LBJForm>) updater);
	}

	public List<LBJFormValidator<LBJForm>> getValidators() {
		if (validators == null) {
			validators = new ArrayList<>();
		}
		return validators;
	}

	@SuppressWarnings("unchecked")
	public boolean addValidator(LBJFormValidator<? extends LBJForm> validator) {
		return getValidators().add((LBJFormValidator<LBJForm>) validator);
	}

}
