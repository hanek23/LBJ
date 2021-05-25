package generator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import constants.XmlPartsSupplier;
import domain.AddColumn;
import domain.AddForeignKeyConstraint;
import domain.AddNotNullConstraint;
import domain.Column;
import domain.CreateIndex;
import domain.CreateTable;
import domain.DropColumn;
import domain.DropForeignKeyConstraint;
import domain.DropIndex;
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
		if (column.isAddNotNullConstraint()) {
			return addNotNullConstraint((AddNotNullConstraint) column);
		}
		if (column.isCreateIndex()) {
			return createIndex((CreateIndex) column);
		}
		if (column.isDropIndex()) {
			return dropIndex((DropIndex) column);
		}
		if (column.isAddForeignKeyConstraint()) {
			return addForeignKeyConstraint((AddForeignKeyConstraint) column);
		}
		if (column.isDropForeignKeyConstraint()) {
			return dropForeignKeyConstraint((DropForeignKeyConstraint) column);
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

	private static String addNotNullConstraint(AddNotNullConstraint constraint) {
		String addNotNullConstraint = XmlPartsSupplier.getAddNotNullConstraintBase(constraint);
		addNotNullConstraint = XmlPartsSupplier.replaceTableName(addNotNullConstraint, constraint.getTableName());
		addNotNullConstraint = XmlPartsSupplier.replaceColumnName(addNotNullConstraint, constraint);
		addNotNullConstraint = XmlPartsSupplier.replaceColumnDataType(addNotNullConstraint, constraint);
		return addNotNullConstraint;
	}

	private static String dropColumn(DropColumn column) {
		String xmlDropColumn = "";
		if (column.hasIndex()) {
			xmlDropColumn += dropIndex(column);
		}
		if (column.hasForeignKey()) {
			xmlDropColumn += XmlPartsSupplier.getDropForeignKeyConstraintBase();
			xmlDropColumn = XmlPartsSupplier.replaceColumnForeignKeyName(xmlDropColumn, (Column) column);
			xmlDropColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedColumn(xmlDropColumn, (Column) column);
			xmlDropColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedTable(xmlDropColumn, (Column) column);
		}
		xmlDropColumn += XmlPartsSupplier.getDropColumnBase();
		xmlDropColumn = XmlPartsSupplier.replaceDropColumnDefaultValue(xmlDropColumn, column);
		xmlDropColumn = XmlPartsSupplier.replaceTableName(xmlDropColumn, column.getTableName());
		xmlDropColumn = XmlPartsSupplier.replaceColumnName(xmlDropColumn, column);
		return xmlDropColumn;
	}

	private static String addColumn(AddColumn column) {
		String xmlColumn = XmlPartsSupplier.getAddColumnBase(column);
		xmlColumn = handleConstraints(column, xmlColumn);
		xmlColumn = handleDefaultValue(column, xmlColumn);
		if (column.hasIndex()) {
			xmlColumn += createIndex((CreateIndex) column);
		}

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

	private static String createIndex(CreateIndex column) {
		String createIndex = XmlPartsSupplier.getColumnIndexBase(column);
		createIndex = XmlPartsSupplier.replaceColumnIndexName(createIndex, column);
		createIndex = XmlPartsSupplier.replaceTableName(createIndex, column.getTableName());
		createIndex = XmlPartsSupplier.replaceColumnName(createIndex, column);
		return createIndex;
	}

	private static String dropIndex(DropIndex column) {
		String dropIndex = XmlPartsSupplier.getDropIndexBase(column);
		dropIndex = XmlPartsSupplier.replaceColumnIndexName(dropIndex, column);
		dropIndex = XmlPartsSupplier.replaceTableName(dropIndex, column.getTableName());
		return dropIndex;
	}

	private static String addForeignKeyConstraint(AddForeignKeyConstraint column) {
		String xmlColumn = XmlPartsSupplier.getAddForeignKeyConstraintBase();
		xmlColumn = XmlPartsSupplier.addColumnConstraintsForeignKey(xmlColumn);
		xmlColumn = XmlPartsSupplier.replaceTableName(xmlColumn, column.getTableName());
		xmlColumn = XmlPartsSupplier.replaceColumnName(xmlColumn, column);
		xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyName(xmlColumn, (Column) column);
		xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedColumn(xmlColumn, (Column) column);
		xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyReferencedTable(xmlColumn, (Column) column);
		return xmlColumn;
	}

	private static String dropForeignKeyConstraint(DropForeignKeyConstraint column) {
		String xmlColumn = XmlPartsSupplier.getDropForeignKeyConstraintBase();
		xmlColumn = XmlPartsSupplier.replaceTableName(xmlColumn, column.getTableName());
		xmlColumn = XmlPartsSupplier.replaceColumnForeignKeyName(xmlColumn, (Column) column);
		return xmlColumn;
	}

}
