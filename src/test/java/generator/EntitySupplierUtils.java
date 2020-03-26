package generator;

import org.apache.commons.lang3.StringUtils;

import domain.Column;
import domain.ColumnOperation;
import domain.ForeignKey;
import domain.Table;

public class EntitySupplierUtils {

	private EntitySupplierUtils() {
		// only static methods
	}

	public static Column createColumn(String name, ColumnOperation operation, String tableName, String indexName,
			ForeignKey foreignKey, boolean nullable, String dataType) {
		Column column = new Column(name, operation);
		column.setTableName(tableName);
		column.setDataType(dataType);
		column.setNullable(nullable);
		column.setIndex(!StringUtils.isBlank(indexName));
		column.setIndexName(indexName);
		column.setForeignKey(foreignKey);
		return column;
	}

	public static Table createTable(String name, String primaryKeyColumnName, String primaryKeyContrainName,
			String sequenceName) {
		Table table = new Table(name);
		table.setPrimaryKeyColumnName(primaryKeyColumnName);
		table.setPrimaryKeyContrainName(primaryKeyContrainName);
		table.setSequenceName(sequenceName);
		return table;
	}

}
