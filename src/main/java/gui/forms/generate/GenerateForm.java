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
import gui.suppliers.LBJUpdaterSupplier;
import gui.suppliers.LBJValidatorSupplier;
import gui.utils.LBJFormUtils;
import transformers.FormToEntityTransformer;

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

		startingIdTextBox = new LBJTextBoxBuilder(Labels.GENERATE_FORM_CHANGESETS_STARTING_ID, this).disabled()
				.numbersOnly().required().build();

		databasesLabel = new LBJPlainLabelBuilder(Labels.GENERATE_FORM_DATABASES, this).build();
		oracleCheckBox = new LBJCheckBoxBuilder(Labels.GENERATE_FORM_DATABASES_ORACLE, this).checked().build();
		mssqlCheckBox = new LBJCheckBoxBuilder(Labels.GENERATE_FORM_DATABASES_MSSQL, this).checked().build();
		postgreCheckBox = new LBJCheckBoxBuilder(Labels.GENERATE_FORM_DATABASES_POSTGRESQL, this).checked().build();

		generatedXmlTextBox = new LBJTextBoxBuilder(Labels.GENERATE_FORM_GENERATED_XML, this).build();

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
				List<Entity> entities = FormToEntityTransformer.transform(GenerateForm.this);
				generatedXmlTextBox.setValue(Generator.generate(entities, createGeneratorSettings()));
				generatedXmlTextBox.getTextBox().setPreferredSize(new TerminalSize(Settings.GUI_NUMBER_OF_COLUMNS, 10));
				copyToClipboardButton.setEnabled(true);
			}

		});
	}

	@Override
	public void addFormUpdaters() {
		addUpdater(LBJUpdaterSupplier.getGenerateFormStartingIdUpdater());

	}

	@Override
	public void addFormValidators() {
		addValidator(LBJValidatorSupplier.getGenerateFormDatabasesValidator());

	}

	@Override
	public void addComponentsToContent() {
		LBJFormUtils.addComponentTo(this, authorTextBox);
		LBJFormUtils.addComponentTo(this, onlyChangeSetsCheckBox);
		LBJFormUtils.addComponentTo(this, startingIdTextBox);
		LBJFormUtils.addComponentTo(this, databasesLabel);
		LBJFormUtils.addComponentTo(this, oracleCheckBox);
		LBJFormUtils.addComponentTo(this, mssqlCheckBox);
		LBJFormUtils.addComponentTo(this, postgreCheckBox);
		LBJFormUtils.addHiddenTextAreaTo(this, generatedXmlTextBox);

	}

	@Override
	public void addButtonsToContent() {
		LBJFormUtils.addButtonTo(this, generateButton);
		LBJFormUtils.addButtonTo(this, copyToClipboardButton);
	}

	@Override
	public String toString() {
		return Labels.GENERATE_FORM;
	}

	protected GeneratorSettings createGeneratorSettings() {
		GeneratorSettings settings = new GeneratorSettings();
		settings.setAuthor(authorTextBox.getValue());
		settings.setOnlyChangeSets(onlyChangeSetsCheckBox.isChecked());
		settings.setStartingId(Integer.parseInt(startingIdTextBox.getValue()));
		return settings;
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
