package gui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.Button.Listener;

import constants.Labels;
import constants.Settings;
import domain.Table;
import generator.Generator;
import generator.Operation;
import gui.utils.LBJFormUtils;

public class GenerateForm implements Runnable {

	private Window window;
	private Runnable previousWindow;
	private Panel content;
	private Table table;
	private Operation operation;
	private Label authorLabel;
	private TextBox author;
	private TextBox generatedXml;
	private Button copyToClipboradButton;

	public GenerateForm(Window window, Runnable previousWindow, Table table, Operation operation) {
		this.window = window;
		this.previousWindow = previousWindow;
		this.table = table;
		this.operation = operation;
		initialize();
	}

	@Override
	public void run() {
		window.setComponent(content);
	}

	private void initialize() {
		initializeComponents();
		addComponentsToContent();
		addButtonsToContent();
	}

	private void addButtonsToContent() {
		Button backButton = new Button(Labels.BUTTON_BACK);
		content.addComponent(backButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		backButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				previousWindow.run();
			}
		});

		Button generateButton = new Button(Labels.BUTTON_GENERATE);
		content.addComponent(generateButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		generateButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				if (!GenerateForm.this.validate()) {
					return;
				}
				generatedXml.setText(Generator.generate(table, operation, author.getText()));
				generatedXml.setPreferredSize(new TerminalSize(23, 17));
				copyToClipboradButton.setEnabled(true);
			}
		});

		copyToClipboradButton = new Button(Labels.BUTTON_COPY_TO_CLIPBOARD);
		copyToClipboradButton.setEnabled(false);
		content.addComponent(copyToClipboradButton.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
		copyToClipboradButton.addListener(new Listener() {

			@Override
			public void onTriggered(Button button) {
				StringSelection stringSelection = new StringSelection(generatedXml.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
	}

	protected boolean validate() {
		return GuiValidator.validateTextBox(author, authorLabel);
	}

	private void addComponentsToContent() {
		LBJFormUtils.addLabelAndComponentToContent(authorLabel, author, content);
		LBJFormUtils.addComponentToContent(content, generatedXml);
		content.addComponent(generatedXml.setPreferredSize(new TerminalSize(0, 0)));
	}

	private void initializeComponents() {
		content = new Panel(new GridLayout(Settings.GUI_NUMBER_OF_COLUMNS));
		GridLayout gridLayout = (GridLayout) content.getLayoutManager();
		gridLayout.setVerticalSpacing(Settings.GUI_VERTICAL_SPACING);

		authorLabel = new Label(Labels.GENERATE_AUTHOR);
		author = new TextBox();

		generatedXml = new TextBox();
	}

}
