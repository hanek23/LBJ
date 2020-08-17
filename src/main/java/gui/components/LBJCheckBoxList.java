package gui.components;

import java.util.List;

import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.Component;

import gui.forms.LBJForm;

/**
 * List of {@link String} values where each item has its own check box.
 */
public class LBJCheckBoxList<T extends LBJComponentValueHolder> extends LBJValueComponent<List<T>> {

	private CheckBoxList<T> checkBoxList;

	public LBJCheckBoxList(String name, LBJForm form) {
		super(name, form);
	}

	/**
	 * @return list of checked values
	 */
	@Override
	public List<T> getValue() {
		return getCheckBoxList().getCheckedItems();
	}

	@Override
	public void setValue(List<T> items) {
		getCheckBoxList().clearItems();
		for (T item : items) {
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

	public T getFocusedItem() {
		return getCheckBoxList().getSelectedItem();
	}

	public CheckBoxList<T> getCheckBoxList() {
		return checkBoxList;
	}

	public void setCheckBoxList(CheckBoxList<T> checkBoxList) {
		this.checkBoxList = checkBoxList;
	}
	
	public void addItem(T item) {
		getCheckBoxList().addItem(item);
	}

}
