package gui.updaters.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import constants.NamingConventions;
import gui.components.LBJComponent;
import gui.components.LBJTextBox;
import gui.components.LBJValueHolderComponent;
import gui.forms.LBJForm;
import gui.updaters.LBJValueUpdater;

/**
 * Updates components {@link String} value to to its {@link NamingConventions}
 * value. For examle Index name might be define as
 * I_€{TABLE_NAME}€_€{COLUMN_NAME}€, job of this updater would then be to find
 * components with name "Table name" and "Column name" get their values and
 * replace them accordingly in Index name so it looks something like I_LBJ_ID
 * (Table name: LBJ, Column name: id)
 */
public class LBJNamingConventionUpdater implements LBJValueUpdater<String> {

	@Override
	public void update(LBJValueHolderComponent<String> component) {
		// Default implementation of LBJValueHolderComponent<String> is LBJTextBox
		LBJTextBox textBox = (LBJTextBox) component;
		String[] componentNames = NamingConventions.fromNamingConvention(textBox.getNamingConvention());
		Map<String, LBJComponent> componentByName = getComponentByNameOn(component.getForm(), componentNames);
		// Probably should check if it is really String value holder component but meh,
		// if there are duplicate names of components we have got bigger problems
		// somewhere else
		boolean isOneFocused = componentByName.values().stream()
				.anyMatch(c -> ((LBJValueHolderComponent<String>) c).isFocused());
		if (!isOneFocused) {
			// Only updating value if one of related components is focused. This way user
			// can still choose whatever name he/she wishes regardless of naming
			// convention
			return;
		}
		String value = textBox.getNamingConvention();
		for (Entry<String, LBJComponent> entry : componentByName.entrySet()) {
			value = StringUtils.replace(value, NamingConventions.toNamingConvention(entry.getKey()),
					((LBJTextBox) entry.getValue()).getValue());
		}
		component.setValue(value);
	}

	private Map<String, LBJComponent> getComponentByNameOn(LBJForm form, String[] names) {
		Map<String, LBJComponent> componentByName = new HashMap<>();
		for (String name : names) {
			for (LBJComponent component : form.getComponents()) {
				if (component.getName().equalsIgnoreCase(name)) {
					componentByName.put(name, component);
					break;
				}
			}
			if (!componentByName.containsKey(name)) {
				throw new IllegalArgumentException("No component with name: " + name + " found");
			}
		}
		return componentByName;
	}

}
