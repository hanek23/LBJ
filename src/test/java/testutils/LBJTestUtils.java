package testutils;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Interactable;

import gui.components.LBJValueHolderComponent;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;

public class LBJTestUtils {

	private LBJTestUtils() {
		// only static methods
	}

	public static void focus(LBJValueHolderComponent<?> component) {
		if (!(component.getComponent() instanceof Interactable)) {
			throw new IllegalArgumentException("I can only focus interactable components!");
		}
		// ((Interactable) component.getComponent()).onEnterFocus(null, null);
		((Interactable) component.getComponent()).takeFocus();
	}

	public static CreateTableForm getCreateTableForm() {
		return new CreateTableForm(new BasicWindow(), new LBJMockForm());
	}

	public static AddColumnForm getAddColumnForm() {
		return new AddColumnForm(new BasicWindow(), new LBJMockForm());
	}

}
