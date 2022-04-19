package gui.builders;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;

import gui.attribute.Attribute;
import gui.components.LBJComboBoxString;
import gui.forms.LBJForm;
import gui.updaters.shared.LBJRequiredLabelComponentUpdater;
import gui.utils.BeanSupplier;
import gui.utils.LBJCaseUtils;
import gui.validators.shared.LBJStringRequiredValidator;

/**
 * Builder for {@link LBJComboBoxString}.
 */
public class LBJComboBoxStringBuilder {

	private LBJComboBoxString lbjComboBox;

	public LBJComboBoxStringBuilder(Attribute attribute, LBJForm form) {
		lbjComboBox = new LBJComboBoxString(attribute.getLabel(), form);
		lbjComboBox.setComboBox(new ComboBox<>());
		lbjComboBox.setLabel(new Label(attribute.getLabel()));
		lbjComboBox.addValueUpdater(LBJCaseUtils.caseUpdater(attribute.getLetterCase()));
		lbjComboBox.addValueValidator(LBJCaseUtils.getCaseValidator(attribute.getLetterCase()));
		if (attribute.isRequired()) {
			setRequired();
		}
	}

	public LBJComboBoxStringBuilder addItem(String item) {
		lbjComboBox.addItem(item);
		return this;
	}

	public LBJComboBoxStringBuilder selectItem(String item) {
		lbjComboBox.setValue(item);
		return this;
	}

	public LBJComboBoxStringBuilder readOnly(boolean isReadOlny) {
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
