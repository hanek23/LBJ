package gui.forms.generate;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import domain.Entity;
import generator.Generator;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;
import gui.utils.LBJFormUtils;
import transformer.FormsToEntityTransformer;

public class GenerateForm extends LBJWizardForm {

	private LBJTextBox authorTextBox;
	private LBJTextBox generatedXmlTextBox;
	private Button generateButton;
	private Button copyToClipboardButton;

	public GenerateForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		authorTextBox = new LBJTextBoxBuilder(Labels.GENERATE_AUTHOR, this).required().build();
		generatedXmlTextBox = new LBJTextBoxBuilder(Labels.GENERATE_GENERATED_XML, this).build();

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
				Entity entity = FormsToEntityTransformer.transform(GenerateForm.this);
				generatedXmlTextBox.setValue(Generator.generate(entity, authorTextBox.getValue()));
				generatedXmlTextBox.getTextBox().setPreferredSize(new TerminalSize(23, 17));
				copyToClipboardButton.setEnabled(true);
			}
		});
	}

	@Override
	public void addFormUpdaters() {
		// no updaters

	}

	@Override
	public void addFormValidators() {
		// no validators

	}

	@Override
	public void addComponentsToContent() {
		LBJFormUtils.addComponentToContent(getContent(), authorTextBox);
		LBJFormUtils.addHiddenTextAreaToContent(getContent(), generatedXmlTextBox);

	}

	@Override
	public void addButtonsToContent() {
		LBJFormUtils.addBackButton(getContent(), getPreviousForm());
		LBJFormUtils.addButtonToConent(getContent(), generateButton);
		LBJFormUtils.addButtonToConent(getContent(), copyToClipboardButton);
	}

	@Override
	public String toString() {
		return Labels.GENERATE_FORM;
	}

	public LBJTextBox getAuthorTextBox() {
		return authorTextBox;
	}

	public LBJTextBox getGeneratedXmlTextBox() {
		return generatedXmlTextBox;
	}

}
