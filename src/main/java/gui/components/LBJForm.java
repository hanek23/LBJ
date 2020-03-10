package gui.components;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;

public abstract class LBJForm<T> {

	private LBJForm<?> previousForm;
	private LBJForm<?> nextForm;
	private Window window;
	private boolean visible;
	private boolean initialized;
	private Panel content;
	private List<LBJComponent<?>> components;

	public LBJForm(Window window, LBJForm<?> previousForm) {
		this.window = window;
		this.previousForm = previousForm;
		initialize();
		initialized = true;
	}

	public void initialize() {
		initializeComponents();
		addButtonsToContent();
	}

	public abstract void initializeComponents();

	public abstract void addButtonsToContent();

	public abstract void update();

	public abstract T convert();

	public boolean validate() {
		boolean isFormValid = true;
		for (LBJComponent<?> component : components) {
			isFormValid = component.isValid() && isFormValid;
		}
		return isFormValid;
	}

	public void focus() {
		visible = true;
		window.setComponent(content);
	}

	public void goToPreviousForm() {
		visible = false;
		previousForm.focus();
	}

	public void goToNextForm() {
		visible = false;
		nextForm.focus();
	}

	public LBJForm<?> getPreviousForm() {
		return previousForm;
	}

	public void setPreviousForm(LBJForm<?> previousForm) {
		this.previousForm = previousForm;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Panel getContent() {
		return content;
	}

	public void setContent(Panel content) {
		this.content = content;
	}

	public List<LBJComponent<?>> getComponents() {
		if (components == null) {
			components = new ArrayList<>();
		}
		return components;
	}

	public boolean addComponent(LBJComponent<?> component) {
		return getComponents().add(component);
	}

	public LBJForm<?> getNextForm() {
		return nextForm;
	}

	public void setNextForm(LBJForm<?> nextForm) {
		this.nextForm = nextForm;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	@Override
	public abstract String toString();

}
