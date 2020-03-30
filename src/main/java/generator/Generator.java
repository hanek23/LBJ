package generator;

import java.util.List;

import domain.AddColumn;
import domain.Column;
import domain.Entity;
import domain.GeneralColumn;
import domain.RemoveNotNullConstraint;
import domain.Table;

/**
 * Class for converting {@link Table}, {@link AddColumn},
 * {@link RemoveNotNullConstraint} etc. to Liquibase changelog or changesets.
 */
public class Generator {

	private Generator() {
		// only static methods
	}

	public static String generate(List<Entity> entities, GeneratorSettings settings) {
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
		return XmlBuilder.finish(changesets, settings);
	}

	private static String createColumnChangeset(GeneralColumn column) {
		if (column.isAddColumn()) {
			return addColumn((AddColumn) column);
		}
		if (column.isRemoveNotNullConstraint()) {
			return removeNotNullConstraint((RemoveNotNullConstraint) column);
		}
		throw new UnsupportedOperationException(
				"Generator not yet implemeted for: " + column.getClass().getSimpleName());
	}

	private static String removeNotNullConstraint(RemoveNotNullConstraint constraint) {
		String removeNotNullConstraint = XmlBuilder.getRemoveNotNullConstraintBase(constraint);
		removeNotNullConstraint = XmlBuilder.replaceTableName(removeNotNullConstraint, constraint.getTableName());
		removeNotNullConstraint = XmlBuilder.replaceColumnName(removeNotNullConstraint, constraint);
		removeNotNullConstraint = XmlBuilder.replaceColumnDataType(removeNotNullConstraint, constraint);
		return removeNotNullConstraint;
	}

	private static String addColumn(AddColumn column) {
		String xmlColumn = XmlBuilder.getColumnBase(column);
		xmlColumn = handleConstraints(column, xmlColumn);
		xmlColumn = handleIndex(column, xmlColumn);

		xmlColumn = XmlBuilder.replaceTableName(xmlColumn, column.getTableName());
		xmlColumn = XmlBuilder.replaceColumnName(xmlColumn, column);
		xmlColumn = XmlBuilder.replaceColumnDataType(xmlColumn, column);
		return xmlColumn;
	}

	private static String handleIndex(AddColumn column, String xmlColumn) {
		if (column.hasIndex()) {
			xmlColumn += XmlBuilder.getColumnIndexBase(column);
			xmlColumn = XmlBuilder.replaceColumnIndexName(xmlColumn, column);
		}
		return xmlColumn;
	}

	private static String handleConstraints(AddColumn column, String xmlColumn) {
		if (column.hasConstrains()) {
			xmlColumn = XmlBuilder.addColumnConstraintsBase(xmlColumn);
			xmlColumn = handleForeignKey(column, xmlColumn);
			xmlColumn = XmlBuilder.replaceColumnNullable(xmlColumn, column);
		} else {
			xmlColumn = XmlBuilder.removeConstraints(xmlColumn);
		}
		return xmlColumn;
	}

	private static String handleForeignKey(AddColumn column, String xmlColumn) {
		if (column.hasForeignKey()) {
			xmlColumn = XmlBuilder.addColumnConstraintsForeignKey(xmlColumn);
			xmlColumn = XmlBuilder.replaceColumnForeignKeyName(xmlColumn, column);
			xmlColumn = XmlBuilder.replaceColumnForeignKeyReferencedColumn(xmlColumn, column);
			xmlColumn = XmlBuilder.replaceColumnForeignKeyReferencedTable(xmlColumn, column);
		} else {
			xmlColumn = XmlBuilder.removeColumnForeignKeyConstraint(xmlColumn);
		}
		return xmlColumn;
	}

	private static String createTable(Table table) {
		String createTable = XmlBuilder.getTableBase(table);
		createTable = XmlBuilder.replaceTableName(createTable, table.getName());
		createTable = XmlBuilder.replaceColumnPrimaryKeyName(createTable, table);
		createTable = XmlBuilder.replaceConstrainPrimaryKeyName(createTable, table);
		createTable = XmlBuilder.replaceSequenceName(createTable, table);
		return createTable;
	}

}
