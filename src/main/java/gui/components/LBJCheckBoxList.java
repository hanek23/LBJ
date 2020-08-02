package gui.components;

import java.util.List;

import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.Component;

import gui.forms.LBJForm;

/**
 * List of {@link String} values where each item has its own check box.
 */
public class LBJCheckBoxList extends LBJValueComponent<List<String>> {

	private CheckBoxList<String> checkBoxList;

	public LBJCheckBoxList(String name, LBJForm form) {
		super(name, form);
	}

	/**
	 * @return list of checked values
	 */
	@Override
	public List<String> getValue() {
		return getCheckBoxList().getCheckedItems();
	}

	@Override
	public void setValue(List<String> items) {
		getCheckBoxList().clearItems();
		for (String item : items) {
			getCheckBoxList().addItem(item);
		}
	}

	@Override
	public Component getComponent() {
		return getCheckBoxList();
	}

	@Override
	public boolean isFocused() {
		return getCheckBoxList().isFocused();
	}

	public String getFocusedItem() {
		return getCheckBoxList().getSelectedItem();
	}

	public CheckBoxList<String> getCheckBoxList() {
		return checkBoxList;
	}

	public void setCheckBoxList(CheckBoxList<String> checkBoxList) {
		this.checkBoxList = checkBoxList;
	}

}
