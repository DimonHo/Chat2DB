package ai.chat2db.server.domain.core.cache;

import org.springframework.util.StringUtils;

public class CacheKey {

    public static String getLoginUserKey(Long userId) {
        return "login_user_" + userId;
    }

    public static String getDataSourceKey(Long dataSourceId) {
        return "schemas_datasourceId_" + dataSourceId;
    }

    public static String getDataBasesKey(Long dataSourceId) {
        return "databases_datasourceId_" + dataSourceId;
    }

    public static String getSchemasKey(Long dataSourceId, String databaseName) {

        return "databases_datasourceId_" + dataSourceId + "_databaseName_" + databaseName;
    }

    public static String getTableKey(Long dataSourceId, String databaseName, String schemaName) {
        StringBuffer stringBuffer = new StringBuffer("tables_dataSourceId" + dataSourceId);
        if (!StringUtils.isEmpty(databaseName)) {
            stringBuffer.append("_databaseName" + databaseName);
        }
        if (!StringUtils.isEmpty(schemaName)) {
            stringBuffer.append("_schemaName" + schemaName);
        }
        return stringBuffer.toString();
    }

    public static String getColumnKey(Long dataSourceId, String databaseName, String schemaName,String tableName) {
        StringBuffer stringBuffer = new StringBuffer("columns_dataSourceId" + dataSourceId);
        if (!StringUtils.isEmpty(databaseName)) {
            stringBuffer.append("_databaseName" + databaseName);
        }
        if (!StringUtils.isEmpty(schemaName)) {
            stringBuffer.append("_schemaName" + schemaName);
        }
        stringBuffer.append("_tableName"+tableName);
        return stringBuffer.toString();
    }
}
