package generator;

import org.apache.commons.lang3.StringUtils;

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
		}
		if (operation == Operation.CREATE_TABLE) {
			changesets = createTable(table);
			operation = Operation.ADD_COLUMN;
		}
		if (operation == Operation.ADD_COLUMN) {
			for (Column column : table.getColumns()) {
				changesets += addColumn(column);
			}
		}
		changesets = XmlBuilder.replaceAuthor(changesets, author);
		return XmlBuilder.toChangelog(changesets);
	}

	private static String removeNotNullConstraint(Table table) {
		if (table.getColumns().size() > 1) {
			throw new UnsupportedOperationException(
					"I was not taught how to remove multiple not null constraints at once");
		}
		String removeNotNullConstraint = XmlParts.COLUMN_REMOVE_NOT_NULL_CONSTRAINT;
		removeNotNullConstraint = XmlBuilder.replaceTableName(removeNotNullConstraint, table.getName());
		removeNotNullConstraint = XmlBuilder.replaceTableNameLowerCase(removeNotNullConstraint, table.getName());
		removeNotNullConstraint = XmlBuilder.replaceColumnName(removeNotNullConstraint,
				table.getColumns().get(0).getName());
		removeNotNullConstraint = XmlBuilder.replaceColumnNameUpperCase(removeNotNullConstraint,
				table.getColumns().get(0).getName());
		removeNotNullConstraint = XmlBuilder.replaceColumnDataType(removeNotNullConstraint,
				table.getColumns().get(0).getDataType());
		return removeNotNullConstraint;
	}

	private static String addColumn(Column column) {
		String addColumn;
		if (StringUtils.equalsIgnoreCase(column.getDataType(), "boolean")) {
			addColumn = XmlParts.ADD_COLUMN_BOOLEAN_ORACLE_MSSQL;
			addColumn += XmlParts.ADD_COLUMN_BOOLEAN_POSTGRESQL;
		} else {
			addColumn = XmlParts.ADD_COLUMN;
		}
		if (column.hasForeignKey() || !column.isNullable()) {
			addColumn = XmlBuilder.addColumnConstraints(addColumn);
			if (column.hasForeignKey()) {
				addColumn = XmlBuilder.addColumnForeignKeyConstraint(addColumn);
				addColumn = XmlBuilder.replaceColumnForeignKeyName(addColumn, column.getForeignKey().getName());
				addColumn = XmlBuilder.replaceColumnForeignKeyReferencedColumn(addColumn,
						column.getForeignKey().getReferencedColumn());
				addColumn = XmlBuilder.replaceColumnForeignKeyReferencedTable(addColumn,
						column.getForeignKey().getReferencedTable());
			} else {
				addColumn = XmlBuilder.removeColumnForeignKeyConstraint(addColumn);
			}
			if (column.isNullable()) {
				addColumn = XmlBuilder.removeNullable(addColumn);
			} else {
				addColumn = XmlBuilder.addColumnNullableFalse(addColumn);
			}
		} else {
			addColumn = XmlBuilder.removeConstraints(addColumn);
		}
		if (column.hasIndex()) {
			addColumn += XmlParts.COLUMN_INDEX;
			addColumn = XmlBuilder.replaceColumnIndexName(addColumn, column.getIndexName());
		}
		addColumn = XmlBuilder.replaceTableName(addColumn, column.getTable().getName());
		addColumn = XmlBuilder.replaceColumnName(addColumn, column.getName());
		addColumn = XmlBuilder.replaceColumnDataType(addColumn, column.getDataType());
		return addColumn;
	}

	private static String createTable(Table table) {
		String createTable = null;
		if (table.isForMssql()) {
			createTable = XmlParts.CREATE_TABLE_MSSQL;
		}
		if (table.isForOracle() || table.isForPostgreSql()) {
			createTable += XmlParts.CREATE_TABLE_ORACLE_POSTGRESQL;
			if (table.isForOracle()) {
				createTable += XmlParts.CREATE_TABLE_SEQUENCE_ORACLE;
			}
			if (table.isForPostgreSql()) {
				createTable += XmlParts.CREATE_TABLE_SEQUENCE_POSTGRESQL;
			}
		}
		createTable = XmlBuilder.replaceTableName(createTable, table.getName());
		createTable = XmlBuilder.replaceColumnPrimaryKeyName(createTable, table.getPrimaryKeyColumnName());
		createTable = XmlBuilder.replaceConstrainPrimaryKeyName(createTable, table.getPrimaryKeyContrainName());
		createTable = XmlBuilder.replaceSequenceName(createTable, table.getSequenceName());
		return createTable;
	}

}
