package generator;

import domain.Table;

public class Generator {

	private Generator() {
		// only static methods
	}

	public static String generate(Table table, Operation operation) {
		return "Generating table: " + table.getName() + " with " + table.getColumns().size() + " columns";
	}

}
