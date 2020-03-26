package generator;

import java.util.List;

import constants.XmlParts;
import domain.AddColumn;
import domain.Column;
import domain.Entity;
import domain.GeneralColumn;
import domain.RemoveNotNullConstraint;
import domain.Table;

/**
 * Class for converting {@link Table}, {@link Column} etc. to Liquibase
 * changelog.
 */
public class Generator {

	private Generator() {
		// only static methods
	}

	public static String generate(List<Entity> entities, String author) {
		String changesets = "";
		for (Entity entity : entities) {
			if (entity instanceof Table) {
				changesets += createTable((Table) entity);
				continue;
			}
			if (entity instanceof Column) {
				changesets += createColumnChangeset((GeneralColumn) entity);
			}
		}
		changesets = XmlBuilder.replaceAuthor(changesets, author);
		return XmlBuilder.toChangelog(changesets);
	}

	private static String createColumnChangeset(GeneralColumn column) {
		if (column.isAddColumn()) {
			return addColumn((AddColumn) column);
		}
		if (column.isRemoveNotNullConstraint()) {
			return removeNotNullConstraint((RemoveNotNullConstraint) column);
		}
		throw new IllegalArgumentException("Generator not yet implemeted for: " + column.getClass().getSimpleName());
	}

	private static String removeNotNullConstraint(RemoveNotNullConstraint constraint) {
		String removeNotNullConstraint = XmlParts.getRemoveNotNullConstraint();
		removeNotNullConstraint = XmlBuilder.replaceTableName(removeNotNullConstraint, constraint.getTableName());
		removeNotNullConstraint = XmlBuilder.replaceColumnName(removeNotNullConstraint, constraint);
		removeNotNullConstraint = XmlBuilder.replaceColumnDataType(removeNotNullConstraint, constraint);
		return removeNotNullConstraint;
	}

	private static String addColumn(AddColumn column) {
		String addColumn = "";
		addColumn = addColumnBase(column);
		addColumn = handleConstraints(column, addColumn);
		addColumn = handleIndex(column, addColumn);

		addColumn = XmlBuilder.replaceTableName(addColumn, column.getTableName());
		addColumn = XmlBuilder.replaceColumnName(addColumn, column);
		addColumn = XmlBuilder.replaceColumnDataType(addColumn, column);
		return addColumn;
	}

	/**
	 * Boolean has different data types for different databases
	 */
	private static String addColumnBase(AddColumn column) {
		if (column.isTypeBoolean()) {
			return addBooleanColumnBase(column);
		}
		return XmlParts.getAddColumn();
	}

	private static String addBooleanColumnBase(AddColumn column) {
		String addColumn = "";
		if (column.isForOracle() || column.isForMssql()) {
			addColumn = XmlParts.getAddColumnBooleanOracleMssql();
		}
		if (column.isForPostgreSql()) {
			addColumn += XmlParts.getAddColumnBooleanPostgre();
		}
		return addColumn;
	}

	private static String handleIndex(AddColumn column, String addColumn) {
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

	private static String handleConstraints(AddColumn column, String addColumn) {
		if (column.hasConstrains()) {
			addColumn = XmlBuilder.addColumnConstraints(addColumn);
			addColumn = handleForeignKey(column, addColumn);
			addColumn = handleNullable(column, addColumn);
		} else {
			addColumn = XmlBuilder.removeConstraints(addColumn);
		}
		return addColumn;
	}

	private static String handleNullable(AddColumn column, String addColumn) {
		addColumn = XmlBuilder.replaceColumnNullable(addColumn, column);
		return addColumn;
	}

	private static String handleForeignKey(AddColumn column, String addColumn) {
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
		createTable = XmlBuilder.replaceTableName(createTable, table.getName());
		createTable = XmlBuilder.replaceColumnPrimaryKeyName(createTable, table);
		createTable = XmlBuilder.replaceConstrainPrimaryKeyName(createTable, table);
		createTable = XmlBuilder.replaceSequenceName(createTable, table);

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
