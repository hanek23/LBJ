package testutils;

import javax.xml.transform.stream.StreamSource;

import org.xmlunit.assertj.XmlAssert;

import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import generator.GeneratorTestRunner;
import gui.components.LBJValueComponent;
import gui.forms.addcolumn.AddColumnForm;
import gui.forms.createtable.CreateTableForm;
import gui.forms.dropcolumn.DropColumnForm;
import gui.forms.dropnotnullconstraint.DropNotNullConstraintForm;
import gui.forms.droptable.DropTableForm;
import gui.forms.generate.GenerateForm;
import gui.forms.preferences.PreferencesForm;

public class LBJTestUtils {

	private static final String LIQUIBASE_XSD = "/generator/liquibase-3.8.xsd";

	private LBJTestUtils() {
		// only static methods
	}

	public static void focus(LBJValueComponent<?> component) {
		if (!(component.getComponent() instanceof Interactable)) {
			throw new IllegalArgumentException("I can only focus interactable components!");
		}
		// ((Interactable) component.getComponent()).onEnterFocus(null, null);
		((Interactable) component.getComponent()).takeFocus();
	}

	/**
	 * Hits enter on component
	 */
	public static void click(Interactable interactable) {
		interactable.takeFocus();
		interactable.handleInput(new KeyStroke(KeyType.Enter));
	}

	/**
	 * First it will focus the component then it will set value of component and
	 * updates form to simulate actual user input.
	 */
	public static void setValueOf(LBJValueComponent<?> component, Object value) {
		if (component == null || component.getForm() == null || value == null) {
			throw new IllegalArgumentException("I aint working with null values");
		}
		if (component.forType() != value.getClass()) {
			throw new IllegalArgumentException("Invalid combination of component and value! Type of component: "
					+ component.forType().getSimpleName() + " but type of passed value: "
					+ value.getClass().getSimpleName());
		}
		focus(component);
		component.setRawValue(value);
		component.getForm().update();
	}

	/**
	 * @return {@link CreateTableForm} that is already focused
	 */
	public static CreateTableForm getCreateTableForm() {
		LBJMockForm mockForm = new LBJMockForm();
		CreateTableForm form = new CreateTableForm(mockForm.getWindow(), mockForm);
		form.focus();
		return form;
	}

	/**
	 * @return {@link DropTableForm} that is already focused
	 */
	public static DropTableForm getDropTableForm() {
		LBJMockForm mockForm = new LBJMockForm();
		DropTableForm form = new DropTableForm(mockForm.getWindow(), mockForm);
		form.focus();
		return form;
	}

	/**
	 * @return {@link AddColumnForm} that is already focused
	 */
	public static AddColumnForm getAddColumnForm() {
		LBJMockForm mockForm = new LBJMockForm();
		AddColumnForm form = new AddColumnForm(mockForm.getWindow(), mockForm);
		form.focus();
		return form;
	}

	/**
	 * @return {@link DropColumnForm} that is already focused
	 */
	public static DropColumnForm getDropColumnForm() {
		LBJMockForm mockForm = new LBJMockForm();
		DropColumnForm form = new DropColumnForm(mockForm.getWindow(), mockForm);
		form.focus();
		return form;
	}

	/**
	 * @return {@link DropNotNullConstraintForm} that is already focused
	 */
	public static DropNotNullConstraintForm getDropNotNullConstraintForm() {
		LBJMockForm mockForm = new LBJMockForm();
		DropNotNullConstraintForm form = new DropNotNullConstraintForm(mockForm.getWindow(), mockForm);
		form.focus();
		return form;
	}

	/**
	 * @return {@link GenerateForm} that is already focused
	 */
	public static GenerateForm getGenerateForm() {
		LBJMockForm mockForm = new LBJMockForm();
		GenerateForm form = new GenerateForm(mockForm.getWindow(), mockForm);
		form.focus();
		return form;
	}

	/**
	 * @return {@link PreferencesForm} that is already focused
	 */
	public static PreferencesForm getPreferencesForm() {
		LBJMockForm mockForm = new LBJMockForm();
		PreferencesForm form = new PreferencesForm(mockForm.getWindow(), mockForm);
		form.focus();
		return form;
	}

	public static void assertLiquibaseXmlEquals(String expected, String actual, boolean checkXsd) {
		if (checkXsd) {
			StreamSource liquibaseXsd = new StreamSource(GeneratorTestRunner.class.getResourceAsStream(LIQUIBASE_XSD));
			XmlAssert.assertThat(actual).isValid();
			XmlAssert.assertThat(actual).isValidAgainst(liquibaseXsd);
		}
		XmlAssert.assertThat(actual).and(expected).normalizeWhitespace().areIdentical();
	}

}
