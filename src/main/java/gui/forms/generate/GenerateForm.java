package gui.forms.generate;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import java.util.List;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.Settings;
import domain.Entity;
import generator.Generator;
import generator.GeneratorSettings;
import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJPlainLabelBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJCheckBox;
import gui.components.LBJPlainLabel;
import gui.components.LBJTextBox;
import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;
import gui.suppliers.LBJValidatorSupplier;
import gui.utils.LBJFormUtils;
import transformers.FormsToEntityTransformer;

/**
 * Final form of this application. On this form you must specify Author, also
 * you can specify if you want to generate only changesets and if so from which
 * ID you want the changesets to start. Lastly you can specify for which
 * databases you want the changelog/changesets to be for. After that you can
 * click Generate button and copy generated XML to your clipboard using button
 * Copy to clipboard.
 *
 */
public class GenerateForm extends LBJWizardForm {

	private LBJTextBox authorTextBox;
	private LBJCheckBox onlyChangeSetsCheckBox;
	private LBJTextBox startingIdTextBox;
	private LBJPlainLabel databasesLabel;
	private LBJCheckBox oracleCheckBox;
	private LBJCheckBox mssqlCheckBox;
	private LBJCheckBox postgreCheckBox;
	private LBJTextBox generatedXmlTextBox;

	private Button copyToClipboardButton;
	private Button generateButton;

	public GenerateForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		authorTextBox = new LBJTextBoxBuilder(Labels.GENERATE_FORM_AUTHOR, this).required().build();

		onlyChangeSetsCheckBox = new LBJCheckBoxBuilder(Labels.GENERATE_FORM_ONLY_CHANGESETS, this).build();

		startingIdTextBox = new LBJTextBoxBuilder(Labels.GENERATE_FORM_CHANGESETS_STARTING_ID, this).defaultValue("1")
				.disabled().numbersOnly().required().activatorComponent(onlyChangeSetsCheckBox).build();

		databasesLabel = new LBJPlainLabelBuilder(Labels.GENERATE_FORM_DATABASES, this).build();
		oracleCheckBox = new LBJCheckBoxBuilder(Labels.GENERATE_FORM_DATABASES_ORACLE, this).checked().build();
		mssqlCheckBox = new LBJCheckBoxBuilder(Labels.GENERATE_FORM_DATABASES_MSSQL, this).checked().build();
		postgreCheckBox = new LBJCheckBoxBuilder(Labels.GENERATE_FORM_DATABASES_POSTGRESQL, this).checked().build();

		generatedXmlTextBox = new LBJTextBoxBuilder(Labels.GENERATE_FORM_GENERATED_XML, this).hidden().readOnly()
				.build();

	}

	@Override
	public void initializeButtons() {
		copyToClipboardButton = new Button(Labels.BUTTON_COPY_TO_CLIPBOARD);
		copyToClipboardButton.setEnabled(false);
		copyToClipboardButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				StringSelection stringSelection = new StringSelection(generatedXmlTextBox.getValue());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});

		generateButton = new Button(Labels.BUTTON_GENERATE);
		generateButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				if (!GenerateForm.this.validate()) {
					return;
				}
				List<Entity> entities = FormsToEntityTransformer.transform(GenerateForm.this);
				generatedXmlTextBox.setValue(Generator.generate(entities, createGeneratorSettings()));
				generatedXmlTextBox.getTextBox().setPreferredSize(new TerminalSize(Settings.GUI_NUMBER_OF_COLUMNS, 10));
				copyToClipboardButton.setEnabled(true);
			}

		});
	}

	@Override
	public void addFormUpdaters() {
		// no form updaters
	}

	@Override
	public void addFormValidators() {
		addValidator(LBJValidatorSupplier.getGenerateFormDatabasesValidator());
	}

	@Override
	public void addComponents() {
		LBJFormUtils.addValueAndLabeledComponentTo(this, authorTextBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, onlyChangeSetsCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, startingIdTextBox);
		LBJFormUtils.addLabeledComponentTo(this, databasesLabel);
		LBJFormUtils.addValueAndLabeledComponentTo(this, oracleCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, mssqlCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, postgreCheckBox);
		LBJFormUtils.addValueComponentTo(this, generatedXmlTextBox);

	}

	@Override
	public void addButtons() {
		initializeBackButton();
		LBJFormUtils.addButtonTo(this, generateButton);
		LBJFormUtils.addButtonTo(this, copyToClipboardButton);
	}

	@Override
	public String toString() {
		return Labels.GENERATE_FORM;
	}

	protected GeneratorSettings createGeneratorSettings() {
		return new GeneratorSettings(authorTextBox.getValue(), onlyChangeSetsCheckBox.isChecked(),
				Integer.parseInt(startingIdTextBox.getValue()));
	}

	public LBJTextBox getAuthorTextBox() {
		return authorTextBox;
	}

	public LBJTextBox getGeneratedXmlTextBox() {
		return generatedXmlTextBox;
	}

	public LBJCheckBox getOnlyChangesetsCheckBox() {
		return onlyChangeSetsCheckBox;
	}

	public LBJTextBox getStartingIdTextBox() {
		return startingIdTextBox;
	}

	public LBJPlainLabel getDatabasesLabel() {
		return databasesLabel;
	}

	public LBJCheckBox getOracleCheckBox() {
		return oracleCheckBox;
	}

	public LBJCheckBox getMssqlCheckBox() {
		return mssqlCheckBox;
	}

	public LBJCheckBox getPostgreCheckBox() {
		return postgreCheckBox;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

	public Button getCopyToClipboardButton() {
		return copyToClipboardButton;
	}

}
