package generator;

import java.util.List;

import constants.XmlParts;
import constants.XmlPartsSupplier;
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
		return XmlPartsSupplier.finish(changesets, settings);
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
		String removeNotNullConstraint = XmlPartsSupplier.getRemoveNotNullConstraintBase(constraint);
		removeNotNullConstraint = XmlPartsSupplier.replaceTableName(removeNotNullConstraint, constraint.getTableName());
		removeNotNullConstraint = XmlPartsSupplier.replaceColumnName(removeNotNullConstraint, constraint);
		removeNotNullConstraint = XmlPartsSupplier.replaceColumnDataType(removeNotNullConstraint, constraint);
		return removeNotNullConstraint;
	}

	private static String addColumn(AddColumn column) {
		String xmlColumn = XmlPartsSupplier.getColumnBase(column);
		xmlColumn = handleConstraints(column, xmlColumn);
		xmlColumn = handleIndex(column, xmlColumn);

		xmlColumn = XmlPartsSupplier.replaceTableName(xmlColumn, column.getTableName());
		xmlColumn = XmlPartsSupplier.replaceColumnName(xmlColumn, column);
		xmlColumn = XmlPartsSupplier.replaceColumnDataType(xmlColumn, column);
		return xmlColumn;
	}

	private static String handleIndex(AddColumn column, String xmlColumn) {
		if (column.hasIndex()) {
			xmlColumn += XmlPartsSupplier.getColumnIndexBase(column);
			xmlColumn = XmlPartsSupplier.replaceColumnIndexName(xmlColumn, column);
		}
		return xmlColumn;
	}

	private static String handleConstraints(AddColumn column, String xmlColumn) {
		if (column.hasConstrains()) {
			xmlColumn = XmlPartsSupplier.addColumnConstraintsBase(xmlColumn);
			xmlColumn = handleForeignKey(column, xmlColumn);
			xmlColumn = XmlPartsSupplier.replaceColumnNullable(xmlColumn, column);
		} else {
			xmlColumn = XmlPartsSupplier.removeConstraints(xmlColumn);
		}
		return xmlColumn;
	}

	private static String handleForeignKey(AddColumn column, String xmlColumn) {
		if (column.hasForeignKey()) {
			xmlColumn = XmlPartsSupplier.addColumnConstraintsForeignKey(xmlColumn);
			xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyName(xmlColumn, column);
			xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedColumn(xmlColumn, column);
			xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedTable(xmlColumn, column);
		} else {
			xmlColumn = XmlPartsSupplier.removeColumnForeignKeyConstraint(xmlColumn);
		}
		return xmlColumn;
	}

	private static String createTable(Table table) {
		String createTable = XmlPartsSupplier.getTableBase(table);
		createTable = XmlPartsSupplier.replaceTableName(createTable, table.getName());
		createTable = XmlPartsSupplier.replaceColumnPrimaryKeyName(createTable, table);
		createTable = XmlPartsSupplier.replaceConstrainPrimaryKeyName(createTable, table);
		createTable = XmlPartsSupplier.replaceSequenceName(createTable, table);
		return createTable;
	}

}
