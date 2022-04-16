package gui.builders;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;

import gui.components.LBJComboBox;
import gui.components.LBJComboBoxString;
import gui.forms.LBJForm;
import gui.updaters.shared.LBJRequiredLabelComponentUpdater;
import gui.utils.BeanSupplier;
import gui.validators.LBJValueValidator;
import gui.validators.shared.LBJStringRequiredValidator;

/**
 * Builder for {@link LBJComboBoxString}.
 */
public class LBJComboBoxStringBuilder {

	private LBJComboBoxString lbjComboBox;

	public LBJComboBoxStringBuilder(String name, LBJForm form) {
		lbjComboBox = new LBJComboBoxString(name, form);
		lbjComboBox.setComboBox(new ComboBox<>());
		lbjComboBox.setLabel(new Label(name));
	}

	public LBJComboBoxStringBuilder addItem(String item) {
		lbjComboBox.addItem(item);
		return this;
	}
	
	public LBJComboBoxStringBuilder selectItem(String item) {
		lbjComboBox.setValue(item);
		return this;
	}

	public LBJComboBoxStringBuilder setReadonly(boolean isReadOlny) {
		lbjComboBox.setReadOnly(isReadOlny);
		return this;
	}

	public LBJComboBoxStringBuilder setRequired() {
		lbjComboBox.addUpdater(BeanSupplier.get(LBJRequiredLabelComponentUpdater.class));
		lbjComboBox.addValueValidator(BeanSupplier.get(LBJStringRequiredValidator.class));
		return this;
	}

	public LBJComboBoxString build() {
		return lbjComboBox;
	}

}
