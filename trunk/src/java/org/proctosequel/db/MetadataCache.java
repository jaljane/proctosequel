
package org.proctosequel.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.proctosequel.db.om.DbColumn;
import org.proctosequel.utils.StringHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class MetadataCache {

    private static MetadataCache metadataCache;
    private Map<String, List<String>> schemaTableMapping = new HashMap<>();
    private Map<String, List<DbColumn>> tableDbColumnsMapping = new HashMap<>();
    private Map<String, List<String>> tableColumnsMapping = new HashMap<>();

    private MetadataCache() {
    }

    public static MetadataCache getInstance() {
        synchronized (MetadataCache.class) {
            if (metadataCache == null) {
                metadataCache = new MetadataCache();
                metadataCache.load();
            }
        }
        return metadataCache;
    }

    public void reload(){
        synchronized (MetadataCache.class) {
            schemaTableMapping.clear();
            tableDbColumnsMapping.clear();
            tableColumnsMapping.clear();            
            this.load();
        }
    }
    
    private void load(){
            Connection connection = null;
            try {
                InitialContext context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup(System.getProperty("proctosequel.datasource.naming"));
                String[] schemas = StringHelper.splitAndUpperCase(System.getProperty("proctosequel.schemas"), ";");
                connection = dataSource.getConnection();
                this.putSchemas(connection, Arrays.asList(schemas));
                this.putTables(connection);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }finally{
                try {
                    if(connection!=null && !connection.isClosed())
                    connection.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        
    }
    
    private void putSchemas(Connection connection, List<String> schemas) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getSchemas()) {
            while (resultSet.next()) {
                String schema = resultSet.getString("TABLE_SCHEM");
                if (schemas == null || schemas.isEmpty() || schemas.contains(schema)) {
                    schemaTableMapping.put(schema, null);
                }

            }
        }
    }

    private void putTables(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();

        for (String schema : schemaTableMapping.keySet()) {
            List<String> tableItem = new ArrayList<>();
            try (ResultSet resultSet = metaData.getTables(null, schema, null, null)) {
                while (resultSet.next()) {
                    String table = resultSet.getString("TABLE_NAME");
                    tableItem.add(table);
                    try (ResultSet resultSet2 = metaData.getColumns(null, schema, table, null)) {
                        List<DbColumn> dbColumnItem = new ArrayList<>();
                        List<String> columnItem = new ArrayList<>();
                        while (resultSet2.next()) {
                            DbColumn dbColumn = new DbColumn(resultSet2.getString("COLUMN_NAME"), resultSet2.getInt("DATA_TYPE"));
                            dbColumnItem.add(dbColumn);
                            columnItem.add(resultSet2.getString("COLUMN_NAME"));
                        }
                        tableDbColumnsMapping.put(table, dbColumnItem);
                        tableColumnsMapping.put(table, columnItem);
                    }


                }
            }
            schemaTableMapping.put(schema, tableItem);
        }


    }

    /**
     * @return the schemaTableMapping
     */
    public Map<String, List<String>> getSchemaTableMapping() {
        synchronized (MetadataCache.class) {
            return schemaTableMapping;
        }

    }

    /**
     * @return the TableDbColumnsMapping
     */
    public Map<String, List<DbColumn>> getTableDbColumnsMapping() {
        synchronized (MetadataCache.class) {
            return tableDbColumnsMapping;
        }
    }

    /**
     * @return the TableColumnsMapping
     */
    public Map<String, List<String>> getTableColumnsMapping() {
        synchronized (MetadataCache.class) {
            return tableColumnsMapping;
        }

    }
}
