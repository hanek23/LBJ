package gui.builders;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;

import gui.components.LBJComboBox;
import gui.forms.LBJForm;

/**
 * Builder for {@link LBJComboBox}.
 */
public class LBJComboBoxBuilder<T> {

	private LBJComboBox<T> lbjComboBox;

	public LBJComboBoxBuilder(String name, LBJForm form) {
		lbjComboBox = new LBJComboBox<>(name, form);
		lbjComboBox.setComboBox(new ComboBox<>());
		lbjComboBox.setLabel(new Label(name));
	}

	public LBJComboBoxBuilder<T> addItem(T item) {
		lbjComboBox.addItem(item);
		return this;
	}
	
	public LBJComboBoxBuilder<T> selectItem(T item) {
		lbjComboBox.setValue(item);
		return this;
	}

	public LBJComboBox<T> build() {
		return lbjComboBox;
	}

}
