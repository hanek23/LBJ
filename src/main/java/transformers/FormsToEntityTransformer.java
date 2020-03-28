package transformers;

import java.util.ArrayList;
import java.util.List;

import domain.Entity;
import domain.ForDatabases;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;
import gui.forms.generate.GenerateForm;

public class FormsToEntityTransformer {

	private FormsToEntityTransformer() {
		// only static methods
	}

	public static List<Entity> transform(GenerateForm generateForm) {
		List<Entity> entities = new ArrayList<>();
		LBJForm previousForm = generateForm.getPreviousForm();
		ForDatabases forDatabases = forDatabasesFromGenerateForm(generateForm);
		while (previousForm instanceof LBJEntityForm<?>) {
			Entity entity = ((LBJEntityForm<?>) previousForm).convert();
			entity.setForDatabases(forDatabases);
			entities.add(entity);

			previousForm = ((LBJWizardForm) previousForm).getPreviousForm();
		}
		return entities;
	}

	private static ForDatabases forDatabasesFromGenerateForm(GenerateForm generateForm) {
		ForDatabases forDatabases = new ForDatabases();
		forDatabases.setForOracle(generateForm.getOracleCheckBox().getValue());
		forDatabases.setForMssql(generateForm.getMssqlCheckBox().getValue());
		forDatabases.setForPostgreSql(generateForm.getPostgreCheckBox().getValue());
		return forDatabases;
	}

}
