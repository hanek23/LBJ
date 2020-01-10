package generator;

import domain.Table;

public interface TestTableSupplier {

	Operation getOperation();

	String getExpectedTable();

	Table getTable();

}
