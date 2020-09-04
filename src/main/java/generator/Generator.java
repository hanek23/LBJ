package generator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import constants.XmlPartsSupplier;
import domain.AddColumn;
import domain.Column;
import domain.CreateTable;
import domain.DropColumn;
import domain.DropNotNullConstraint;
import domain.DropTable;
import domain.Entity;
import domain.GeneralColumn;
import domain.GeneralTable;
import domain.ModifyDataType;
import domain.Table;

/**
 * Class for converting {@link Table}, {@link AddColumn},
 * {@link DropNotNullConstraint} etc. to Liquibase changelog or changesets.
 */
public class Generator {

	private Generator() {
		// only static methods
	}

	public static String generate(List<Entity> entities, GeneratorSettings settings) {
		String changesets = "";
		for (Entity entity : entities) {
			if (entity instanceof Table) {
				changesets += createTableChangeset((GeneralTable) entity);
			}
			if (entity instanceof Column) {
				changesets += createColumnChangeset((GeneralColumn) entity);
			}
		}
		return XmlPartsSupplier.finish(changesets, settings);
	}

	private static String createTableChangeset(GeneralTable table) {
		if (table.isCreateTable()) {
			return createTable((CreateTable) table);
		}
		if (table.isDropTable()) {
			return dropTable((DropTable) table);
		}
		throw new UnsupportedOperationException("Generator not yet implemeted for: " + table.getOperation());
	}

	private static String createColumnChangeset(GeneralColumn column) {
		if (column.isAddColumn()) {
			return addColumn((AddColumn) column);
		}
		if (column.isDropNotNullConstraint()) {
			return dropNotNullConstraint((DropNotNullConstraint) column);
		}
		if (column.isDropColumn()) {
			return dropColumn((DropColumn) column);
		}
		if (column.isModifyDataType()) {
			return modifyDataType((ModifyDataType) column);
		}
		throw new UnsupportedOperationException("Generator not yet implemeted for: " + column.getOperation());
	}

	private static String modifyDataType(ModifyDataType column) {
		String modifyDataType = XmlPartsSupplier.getModifyDataTypeBase();
		modifyDataType = XmlPartsSupplier.replaceTableName(modifyDataType, column.getTableName());
		modifyDataType = XmlPartsSupplier.replaceColumnName(modifyDataType, column);
		modifyDataType = XmlPartsSupplier.replaceColumnDataType(modifyDataType, column);
		return modifyDataType;
	}

	private static String dropNotNullConstraint(DropNotNullConstraint constraint) {
		String dropNotNullConstraint = XmlPartsSupplier.getDropNotNullConstraintBase(constraint);
		dropNotNullConstraint = XmlPartsSupplier.replaceTableName(dropNotNullConstraint, constraint.getTableName());
		dropNotNullConstraint = XmlPartsSupplier.replaceColumnName(dropNotNullConstraint, constraint);
		dropNotNullConstraint = XmlPartsSupplier.replaceColumnDataType(dropNotNullConstraint, constraint);
		return dropNotNullConstraint;
	}

	private static String dropColumn(DropColumn column) {
		String xmlDropColumn = "";
		if (column.hasIndex()) {
			xmlDropColumn = XmlPartsSupplier.getDropIndexBase(column);
			xmlDropColumn = XmlPartsSupplier.replaceColumnIndexName(xmlDropColumn, (Column) column);
		}
		if (column.hasForeignKey()) {
			xmlDropColumn += XmlPartsSupplier.getDropForeignKeyBase();
			xmlDropColumn = XmlPartsSupplier.replaceColumnForeignKeyName(xmlDropColumn, (Column) column);
			xmlDropColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedColumn(xmlDropColumn, (Column) column);
			xmlDropColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedTable(xmlDropColumn, (Column) column);
		}
		xmlDropColumn += XmlPartsSupplier.getDropColumnBase();
		xmlDropColumn = XmlPartsSupplier.replaceTableName(xmlDropColumn, column.getTableName());
		xmlDropColumn = XmlPartsSupplier.replaceColumnName(xmlDropColumn, column);
		return xmlDropColumn;
	}

	private static String addColumn(AddColumn column) {
		String xmlColumn = XmlPartsSupplier.getAddColumnBase(column);
		xmlColumn = handleConstraints(column, xmlColumn);
		xmlColumn = handleDefaultValue(column, xmlColumn);
		xmlColumn = handleIndex(column, xmlColumn);

		xmlColumn = XmlPartsSupplier.replaceTableName(xmlColumn, column.getTableName());
		xmlColumn = XmlPartsSupplier.replaceColumnName(xmlColumn, column);
		xmlColumn = XmlPartsSupplier.replaceColumnDataType(xmlColumn, column);
		return xmlColumn;
	}

	private static String handleDefaultValue(AddColumn column, String xmlColumn) {
		if (StringUtils.isNotBlank(column.getDefaultValue())) {
			xmlColumn = XmlPartsSupplier.replaceColumnDefaultValueBase(xmlColumn);
			if (column.isTypeBoolean()) {
				xmlColumn = handleDefaultValueForBoolean(column, xmlColumn);
			} else {
				xmlColumn = XmlPartsSupplier.replaceColumnDefaultValue(xmlColumn, column);
			}
		} else {
			xmlColumn = XmlPartsSupplier.removeColumnDefaultValueBase(xmlColumn);
		}
		return xmlColumn;
	}

	private static String handleDefaultValueForBoolean(AddColumn column, String xmlColumn) {
		if (column.isForOracle() || column.isForMssql()) {
			xmlColumn = XmlPartsSupplier.replaceColumnDefaultValueOracleMssql(xmlColumn, column);
		}
		if (column.isForPostgre()) {
			xmlColumn = XmlPartsSupplier.replaceColumnDefaultValuePostgre(xmlColumn, column);
		}
		return xmlColumn;
	}

	private static String handleIndex(AddColumn column, String xmlColumn) {
		if (column.hasIndex()) {
			xmlColumn += XmlPartsSupplier.getColumnIndexBase(column);
			xmlColumn = XmlPartsSupplier.replaceColumnIndexName(xmlColumn, (Column) column);
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
			xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyName(xmlColumn, (Column) column);
			xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedColumn(xmlColumn, (Column) column);
			xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedTable(xmlColumn, (Column) column);
		} else {
			xmlColumn = XmlPartsSupplier.removeColumnForeignKeyConstraint(xmlColumn);
		}
		return xmlColumn;
	}

	private static String createTable(CreateTable table) {
		String createTable = XmlPartsSupplier.getTableBase(table);
		createTable = XmlPartsSupplier.replaceTableName(createTable, table.getName());
		createTable = XmlPartsSupplier.replaceColumnPrimaryKeyName(createTable, table);
		createTable = XmlPartsSupplier.replaceConstrainPrimaryKeyName(createTable, table);
		createTable = XmlPartsSupplier.replaceSequenceName(createTable, table);
		return createTable;
	}

	private static String dropTable(DropTable table) {
		String dropTable = "";
		if (!StringUtils.isBlank(table.getSequenceName())) {
			dropTable += XmlPartsSupplier.getDropSequenceBase();
		}
		dropTable += XmlPartsSupplier.getDropTableBase();

		dropTable = XmlPartsSupplier.replaceTableName(dropTable, table.getName());
		dropTable = XmlPartsSupplier.replaceSequenceName(dropTable, table);
		return dropTable;
	}

}
