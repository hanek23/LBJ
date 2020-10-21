package generator;

import org.apache.commons.lang3.StringUtils;

import domain.AddColumn;
import domain.Column;
import domain.ColumnOperation;
import domain.CreateTable;
import domain.DropColumn;
import domain.DropTable;
import domain.ForeignKey;
import domain.ModifyDataType;
import domain.Table;
import domain.TableOperation;

public class GeneratorTestUtils {

	private GeneratorTestUtils() {
		// only static methods
	}

	public static AddColumn addColumn(String name, String tableName, String indexName, ForeignKey foreignKey,
			boolean nullable, String dataType, String defaultValue) {
		AddColumn column = new Column(name, ColumnOperation.ADD);
		column.setTableName(tableName);
		column.setDataType(dataType);
		column.setNullable(nullable);
		column.setHasIndex(!StringUtils.isBlank(indexName));
		column.setIndexName(indexName);
		column.setForeignKey(foreignKey);
		column.setDefaultValue(defaultValue);
		return column;
	}

	public static DropColumn dropColumn(String name, String tableName, boolean hasDefaultValue, String indexName,
			ForeignKey foreignKey) {
		DropColumn column = new Column(name, ColumnOperation.DROP);
		column.setTableName(tableName);
		column.setHasDefaultValue(hasDefaultValue);
		column.setHasIndex(!StringUtils.isBlank(indexName));
		column.setIndexName(indexName);
		column.setForeignKey(foreignKey);
		return column;
	}

	public static ModifyDataType modifyDataType(String name, String tableName, String newDataType) {
		ModifyDataType column = new Column(name, ColumnOperation.MODIFY_DATA_TYPE);
		column.setTableName(tableName);
		column.setDataType(newDataType);
		return column;
	}

	public static CreateTable createTable(String name, String primaryKeyColumnName, String primaryKeyContrainName,
			String sequenceName) {
		CreateTable table = new Table(name, TableOperation.CREATE);
		table.setPrimaryKeyColumnName(primaryKeyColumnName);
		table.setPrimaryKeyContrainName(primaryKeyContrainName);
		table.setSequenceName(sequenceName);
		return table;
	}

	public static DropTable dropTable(String name, String sequenceName) {
		DropTable table = new Table(name, TableOperation.DROP);
		table.setSequenceName(sequenceName);
		return table;
	}

}
