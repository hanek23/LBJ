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
			changesets += createColumn(column);
		}
		changesets = XmlBuilder.replaceAuthor(changesets, "phan");
		return XmlBuilder.toChangelog(changesets);
	}

	private static String createColumn(Column column) {
		String createColumn = null;
		return createColumn;
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
