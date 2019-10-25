package generator;

import org.apache.commons.lang3.StringUtils;

import constants.XmlParts;
import domain.Column;
import domain.Table;

public class Generator {

	private Generator() {
		// only static methods
	}

	public static String generate(Table table, Operation operation) {
		String changesets = null;
		if (operation == Operation.CREATE_TABLE) {
			changesets = createTable(table);
		}
		for (Column column : table.getColumns()) {
			changesets += addColumn(column);
		}
		changesets = XmlBuilder.replaceAuthor(changesets, "phan");
		return XmlBuilder.toChangelog(changesets);
	}

	private static String addColumn(Column column) {
		String addColumn = XmlParts.ADD_COLUMN;
		if (column.hasForeignKey() || column.isNullable()) {
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
			if (!column.isNullable()) {
				addColumn = XmlBuilder.addColumnNullableFalse(addColumn);
			} else {
				addColumn = XmlBuilder.removeNullable(addColumn);
			}
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
