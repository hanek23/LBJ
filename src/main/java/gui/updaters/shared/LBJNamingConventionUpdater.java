package gui.updaters.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import constants.NamingConventions;
import gui.components.LBJComponent;
import gui.components.LBJTextBox;
import gui.components.LBJValueComponent;
import gui.forms.LBJForm;
import gui.updaters.LBJValueUpdater;
import gui.utils.SimpleBean;

/**
 * Updates components {@link String} value to to its {@link NamingConventions}
 * value. For examle Index name might be define as
 * I_€{TABLE_NAME}€_€{COLUMN_NAME}€, job of this updater would then be to find
 * components with name "Table name" and "Column name" get their values and
 * replace them accordingly in Index name so it looks something like I_LBJ_ID
 * (Table name: LBJ, Column name: id)
 */
public class LBJNamingConventionUpdater implements LBJValueUpdater<String>, SimpleBean {

	@Override
	@SuppressWarnings("unchecked")
	public void update(LBJValueComponent<String> component) {
		// Default implementation of LBJValueHolderComponent<String> is LBJTextBox
		LBJTextBox textBox = (LBJTextBox) component;
		String[] componentNames = NamingConventions.fromNamingConvention(textBox.getNamingConvention());
		Map<String, LBJComponent> componentByName = getComponentByNameOn(component.getForm(), componentNames);
		// Probably should check if it is really String value holder component but meh,
		// if there are duplicate names of components we have got bigger problems
		// somewhere else
		if (componentByName.isEmpty() && StringUtils.isBlank(textBox.getValue())) {
			// naming convention might not depend on any component (for example someone
			// might want their index name always start with I_ but that is it) so when that
			// is the case and value is empty we will set this as a value
			textBox.setValue(textBox.getNamingConvention());
			return;
		}
		boolean isOneFocused = componentByName.values().stream()
				.anyMatch(c -> ((LBJValueComponent<String>) c).isFocused());
		isOneFocused |= component.isActivatorComponentFocused();
		if (!isOneFocused) {
			// Only updating value if one of related components is focused. This way user
			// can still choose whatever name he/she wishes regardless of naming
			// convention
			return;
		}
		String value = textBox.getNamingConvention();
		boolean allRelatedValuesBlank = true;
		for (Entry<String, LBJComponent> entry : componentByName.entrySet()) {
			String relatedComponentValue = ((LBJTextBox) entry.getValue()).getValue();
			allRelatedValuesBlank &= StringUtils.isBlank(relatedComponentValue);
			value = StringUtils.replace(value, NamingConventions.toNamingConvention(entry.getKey()),
					relatedComponentValue);
		}
		// If all related components are blank, default value should be shown
		textBox.setValue(allRelatedValuesBlank ? textBox.getDefaultValue() : value);
	}

	private Map<String, LBJComponent> getComponentByNameOn(LBJForm form, String[] names) {
		Map<String, LBJComponent> componentByName = new HashMap<>();
		if (names == null) {
			return componentByName;
		}
		for (String name : names) {
			for (LBJComponent component : form.getComponents()) {
				if (component.getName().equalsIgnoreCase(name)) {
					componentByName.put(name, component);
					break;
				}
			}
		}
		return componentByName;
	}

}
