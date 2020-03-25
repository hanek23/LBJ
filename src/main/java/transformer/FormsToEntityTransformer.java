package transformer;

import java.util.ArrayList;
import java.util.List;

import domain.Column;
import domain.Entity;
import domain.Table;
import gui.forms.LBJEntityForm;
import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;
import gui.forms.generate.GenerateForm;

public class FormsToEntityTransformer {

	private FormsToEntityTransformer() {
		// only static methods
	}

	public static Entity transform(GenerateForm generateForm) {
		List<Entity> entities = new ArrayList<>();
		LBJForm previousForm = generateForm.getPreviousForm();
		while (previousForm instanceof LBJEntityForm<?>) {
			entities.add(((LBJEntityForm<?>) previousForm).convert());
			previousForm = ((LBJWizardForm) previousForm).getPreviousForm();
		}
		return merge(entities);
	}

	private static Entity merge(List<Entity> entities) {
		if (entities.isEmpty()) {
			throw new IllegalArgumentException("Number of entities to merge is 0!");
		}
		if (entities.size() == 1) {
			return entities.get(0);
		}
		Table table = findTable(entities);
		entities.remove(table);
		validateAllColumns(entities);
		addColumnsToTable(table, entities);
		return table;
	}

	private static void addColumnsToTable(Table table, List<Entity> entities) {
		for (Entity entity : entities) {
			table.addColumn((Column) entity);
		}
	}

	private static void validateAllColumns(List<Entity> entities) {
		for (Entity entity : entities) {
			if (!(entity instanceof Column)) {
				throw new IllegalArgumentException(
						"Expecting only columns in entities to merge but found: " + entity.getClass().getSimpleName());
			}
		}
	}

	private static Table findTable(List<Entity> entities) {
		Table table = null;
		for (Entity entity : entities) {
			if (entity instanceof Table) {
				if (table != null) {
					throw new IllegalArgumentException("More than one table found in entities to merge!");
				}
				table = (Table) entity;
			}
		}
		return table;
	}

}
