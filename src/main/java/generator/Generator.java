package generator;

import java.util.List;

import constants.XmlParts;
import domain.Column;
import domain.Table;

public class Generator {

	private Generator() {
		// only static methods
	}

	public static String generate(Table table, Operation operation, String author) {
		String changesets = "";
		if (operation == Operation.REMOVE_NOT_NULL_CONSTRAINT) {
			changesets = removeNotNullConstraint(table);
		} else if (operation == Operation.CREATE_TABLE) {
			changesets = createTable(table);
		} else if (operation == Operation.ADD_COLUMN) {
			changesets = addColumns(table.getColumns(), changesets);
		}
		changesets = XmlBuilder.replaceAuthor(changesets, author);
		return XmlBuilder.toChangelog(changesets);
	}

	private static String addColumns(List<Column> columns, String changesets) {
		for (Column column : columns) {
			changesets += addColumn(column);
		}
		return changesets;
	}

	private static String removeNotNullConstraint(Table table) {
		if (table.getColumns().size() > 1) {
			throw new UnsupportedOperationException(
					"I was not taught how to remove multiple not null constraints at once");
		}
		String removeNotNullConstraint = XmlParts.getRemoveNotNullConstraint();
		removeNotNullConstraint = XmlBuilder.replaceTableName(removeNotNullConstraint, table);
		removeNotNullConstraint = XmlBuilder.replaceTableNameLowerCase(removeNotNullConstraint, table);
		removeNotNullConstraint = XmlBuilder.replaceColumnName(removeNotNullConstraint, table.getColumns().get(0));
		removeNotNullConstraint = XmlBuilder.replaceColumnNameUpperCase(removeNotNullConstraint,
				table.getColumns().get(0));
		removeNotNullConstraint = XmlBuilder.replaceColumnDataType(removeNotNullConstraint, table.getColumns().get(0));
		return removeNotNullConstraint;
	}

	private static String addColumn(Column column) {
		String addColumn = "";
		addColumn = addColumnBase(column);
		addColumn = handleConstraints(column, addColumn);
		addColumn = handleIndex(column, addColumn);

		addColumn = XmlBuilder.replaceTableName(addColumn, column.getTable());
		addColumn = XmlBuilder.replaceColumnName(addColumn, column);
		addColumn = XmlBuilder.replaceColumnDataType(addColumn, column);
		return addColumn;
	}

	/**
	 * Boolean has different data types across different databases
	 */
	private static String addColumnBase(Column column) {
		if (column.isTypeBoolean()) {
			return addBooleanColumnBase(column);
		}
		return XmlParts.getAddColumn();
	}

	private static String addBooleanColumnBase(Column column) {
		String addColumn = "";
		if (column.getTable().isForOracle() || column.getTable().isForMssql()) {
			addColumn = XmlParts.getAddColumnBooleanOracleMssql();
		}
		if (column.getTable().isForPostgreSql()) {
			addColumn += XmlParts.getAddColumnBooleanPostgre();
		}
		return addColumn;
	}

	private static String handleIndex(Column column, String addColumn) {
		if (column.hasIndex()) {
			if (column.isForMssql() || column.isForPostgreSql()) {
				addColumn += XmlParts.getColumnIndexMssqlPostgre();
			}
			if (column.isForOracle()) {
				addColumn += XmlParts.getColumnIndexOracle();
			}
			addColumn = XmlBuilder.replaceColumnIndexName(addColumn, column);
		}
		return addColumn;
	}

	private static String handleConstraints(Column column, String addColumn) {
		if (column.hasConstrains()) {
			addColumn = XmlBuilder.addColumnConstraints(addColumn);
			addColumn = handleForeignKey(column, addColumn);
			addColumn = handleNullable(column, addColumn);
		} else {
			addColumn = XmlBuilder.removeConstraints(addColumn);
		}
		return addColumn;
	}

	private static String handleNullable(Column column, String addColumn) {
		addColumn = XmlBuilder.replaceColumnNullable(addColumn, column);
		return addColumn;
	}

	private static String handleForeignKey(Column column, String addColumn) {
		if (column.hasForeignKey()) {
			addColumn = XmlBuilder.addColumnConstraintsForeignKey(addColumn);
			addColumn = XmlBuilder.replaceColumnForeignKeyName(addColumn, column);
			addColumn = XmlBuilder.replaceColumnForeignKeyReferencedColumn(addColumn, column);
			addColumn = XmlBuilder.replaceColumnForeignKeyReferencedTable(addColumn, column);
		} else {
			addColumn = XmlBuilder.removeColumnForeignKeyConstraint(addColumn);
		}
		return addColumn;
	}

	private static String createTable(Table table) {
		String createTable = "";
		createTable = handleTable(table, createTable);
		createTable = XmlBuilder.replaceTableName(createTable, table);
		createTable = XmlBuilder.replaceColumnPrimaryKeyName(createTable, table);
		createTable = XmlBuilder.replaceConstrainPrimaryKeyName(createTable, table);
		createTable = XmlBuilder.replaceSequenceName(createTable, table);

		createTable = addColumns(table.getColumns(), createTable);

		return createTable;
	}

	private static String handleTable(Table table, String createTable) {
		if (table.isForMssql()) {
			createTable += XmlParts.getCreateTableMssql();
		}
		if (table.isForOracle() || table.isForPostgreSql()) {
			createTable += XmlParts.getCreateTableOraclePostgre();
			if (table.isForOracle()) {
				createTable += XmlParts.getCreateTableSequenceOracle();
			}
			if (table.isForPostgreSql()) {
				createTable += XmlParts.getCreateTableSequencePostgre();
			}
		}
		return createTable;
	}

}
