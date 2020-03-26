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
import gui.builders.LBJCheckBoxBuilder;
import gui.builders.LBJPlainLabelBuilder;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJCheckBox;
import gui.components.LBJPlainLabel;
import gui.components.LBJTextBox;
import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;
import gui.utils.LBJFormUtils;
import transformers.FormToEntityTransformer;

public class GenerateForm extends LBJWizardForm {

	private LBJTextBox authorTextBox;
	private LBJCheckBox onlyChangesetsCheckBox;
	private LBJTextBox startingIdTextBox;
	private LBJPlainLabel databasesLabel;
	private LBJCheckBox oracleCheckBox;
	private LBJCheckBox mssqlCheckBox;
	private LBJCheckBox postgreCheckBox;
	private Button generateButton;
	private LBJTextBox generatedXmlTextBox;
	private Button copyToClipboardButton;

	public GenerateForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		authorTextBox = new LBJTextBoxBuilder(Labels.GENERATE_AUTHOR, this).required().build();

		onlyChangesetsCheckBox = new LBJCheckBoxBuilder(Labels.GENERATE_FORM_ONLY_CHANGESETS, this).build();

		startingIdTextBox = new LBJTextBoxBuilder(Labels.GENERATE_FORM_CHANGESETS_STARTING_ID, this).disabled()
				.required().build();

		databasesLabel = new LBJPlainLabelBuilder(Labels.CREATE_TABLE_DATABASES, this).build();
		oracleCheckBox = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_ORACLE, this).checked().build();
		mssqlCheckBox = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_MSSQL, this).checked().build();
		postgreCheckBox = new LBJCheckBoxBuilder(Labels.CREATE_TABLE_DATABASES_POSTGRESQL, this).checked().build();

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
				List<Entity> entities = FormToEntityTransformer.transform(GenerateForm.this);
				generatedXmlTextBox.setValue(Generator.generate(entities, authorTextBox.getValue()));
				// generatedXmlTextBox.setValue(GenerateForm.mockTable());
				generatedXmlTextBox.getTextBox().setPreferredSize(new TerminalSize(Settings.GUI_NUMBER_OF_COLUMNS, 10));
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
		LBJFormUtils.addComponentToContent(getContent(), onlyChangesetsCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), startingIdTextBox);
		LBJFormUtils.addComponentToContent(getContent(), databasesLabel);
		LBJFormUtils.addComponentToContent(getContent(), oracleCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), mssqlCheckBox);
		LBJFormUtils.addComponentToContent(getContent(), postgreCheckBox);
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

	protected static String mockTable() {
		return "<databaseChangeLog xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\n"
				+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
				+ "	xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog\n"
				+ "                        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.4.xsd\"\n"
				+ "	objectQuotingStrategy=\"QUOTE_ONLY_RESERVED_WORDS\">\n"
				+ "	<changeSet id=\"1\" author=\"hanek23\">\n" + "		<preConditions onFail=\"MARK_RAN\">\n"
				+ "			<not>\n"
				+ "				<columnExists tableName=\"NDER_REFERENCE\" columnName=\"nderaction\"/>\n"
				+ "			</not>\n" + "		</preConditions>\n"
				+ "		<comment>Add column nderaction to NDER_REFERENCE</comment>\n"
				+ "		<addColumn tableName=\"NDER_REFERENCE\">\n"
				+ "			<column name=\"nderaction\" type=\"integer\">\n"
				+ "				<constraints foreignKeyName=\"F_NDER_REF_ID_NDER_ACTION\" referencedTableName=\"NDER_ACTION\" referencedColumnNames=\"id_nder_action\"/>\n"
				+ "			</column>\n" + "		</addColumn>\n" + "	</changeSet>\n"
				+ "	<changeSet id=\"2\" author=\"hanek23\" dbms=\"mssql,postgresql\">\n"
				+ "		<preConditions onFail=\"MARK_RAN\">\n" + "			<not>\n"
				+ "				<indexExists indexName=\"I_NDER_REFERENCE_NACTION\"/>\n" + "			</not>\n"
				+ "		</preConditions>\n"
				+ "		<comment>Create index I_NDER_REFERENCE_NACTION if it doesn't exist.</comment>\n"
				+ "		<createIndex tableName=\"NDER_REFERENCE\" indexName=\"I_NDER_REFERENCE_NACTION\">\n"
				+ "			<column name=\"nderaction\"/>\n" + "		</createIndex>\n" + "	</changeSet>\n"
				+ "	<changeSet id=\"3\" author=\"hanek23\" dbms=\"oracle\">\n"
				+ "		<preConditions onFail=\"MARK_RAN\">\n"
				+ "			<sqlCheck expectedResult=\"0\">SELECT count(1) FROM user_indexes WHERE table_name = 'NDER_REFERENCE' and index_name='I_NDER_REFERENCE_NACTION'</sqlCheck>\n"
				+ "		</preConditions>\n"
				+ "		<comment>Create index I_NDER_REFERENCE_NACTION if it doesn't exist.</comment>\n"
				+ "		<createIndex tableName=\"NDER_REFERENCE\" indexName=\"I_NDER_REFERENCE_NACTION\">\n"
				+ "			<column name=\"nderaction\"/>\n" + "		</createIndex>\n" + "	</changeSet>\n"
				+ "</databaseChangeLog>";
	}

	public LBJCheckBox getOnlyChangesetsCheckBox() {
		return onlyChangesetsCheckBox;
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
