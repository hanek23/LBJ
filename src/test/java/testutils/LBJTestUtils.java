package testutils;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Interactable;

import gui.components.LBJValueHolderComponent;
import gui.forms.addcolumn.LBJAddColumnForm;
import gui.forms.createtable.LBJCreateTableForm;

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

	public static LBJCreateTableForm getCreateTableForm() {
		return new LBJCreateTableForm(new BasicWindow(), new LBJMockForm());
	}

	public static LBJAddColumnForm getAddColumnForm() {
		return new LBJAddColumnForm(new BasicWindow(), new LBJMockForm());
	}

}
