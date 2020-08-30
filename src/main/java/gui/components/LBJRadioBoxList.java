package gui.components;

import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.RadioBoxList;

import gui.forms.LBJForm;

public class LBJRadioBoxList<T> extends LBJValueComponent<T> {

	private RadioBoxList<T> radioBoxList;

	public LBJRadioBoxList(String name, LBJForm form) {
		super(name, form);
	}

	@Override
	public Component getComponent() {
		return getRadioBoxList();
	}

	@Override
	public boolean isFocused() {
		return getRadioBoxList().isFocused();
	}

	public T getFocusedItem() {
		return getRadioBoxList().getSelectedItem();
	}

	public void addItem(T item) {
		getRadioBoxList().addItem(item);
	}

	@Override
	public T getValue() {
		return getRadioBoxList().getSelectedItem();
	}

	@Override
	public void setValue(T value) {
		getRadioBoxList().setCheckedItem(value);
	}

	public RadioBoxList<T> getRadioBoxList() {
		return radioBoxList;
	}

	public void setRadioBoxList(RadioBoxList<T> radioBoxList) {
		this.radioBoxList = radioBoxList;
	}

}
