package gui.components;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Component;

import gui.forms.LBJForm;

public class LBJComboBox<T> extends LBJValueComponent<T> {

	private ComboBox<T> comboBox;

	public LBJComboBox(String name, LBJForm form) {
		super(name, form);
	}

	@Override
	public Component getComponent() {
		return getComboBox();
	}

	@Override
	public boolean isFocused() {
		return getComboBox().isFocused();
	}

	public T getFocusedItem() {
		return getComboBox().getSelectedItem();
	}

	public void addItem(T item) {
		getComboBox().addItem(item);
	}

	@Override
	public T getValue() {
		return getComboBox().getSelectedItem();
	}

	@Override
	public void setValue(T value) {
		getComboBox().setSelectedItem(value);
	}

	public ComboBox<T> getComboBox() {
		return comboBox;
	}

	public void setComboBox(ComboBox<T> comboBox) {
		this.comboBox = comboBox;
	}

	public void setReadOnly(boolean readOnly){
		comboBox.setReadOnly(readOnly);
	}


}
