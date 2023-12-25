package ai.chat2db.spi.util;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jipengfei
 * @version : ResultSetUtils.java
 */
@Slf4j
public class ResultSetUtils {



    private static List<String> getRsHeader(ResultSet rs) {
        try {
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int col = resultSetMetaData.getColumnCount();
            List<String> headerList = Lists.newArrayListWithExpectedSize(col);
            for (int i = 1; i <= col; i++) {
                headerList.add(getColumnName(resultSetMetaData, i));
            }
            return headerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param rs
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> List<T> toObjectList(ResultSet rs, Class<T> clazz) {
        try {
            if (rs == null || clazz == null) {
                return Lists.newArrayList();
            }
            List<T> list = Lists.newArrayList();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int col = rsMetaData.getColumnCount();
            List<String> headerList = getRsHeader(rs);
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= col; i++) {
                    map.put(headerList.get(i-1), rs.getObject(i));
                }
                T obj = mapper.convertValue(map, clazz);

                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getColumnName(ResultSetMetaData resultSetMetaData, int column) throws SQLException {
        String columnLabel = resultSetMetaData.getColumnLabel(column);
        if (columnLabel != null) {
            return columnLabel;
        }
        return resultSetMetaData.getColumnName(column);
    }

    /**
     * 获取列值, 如果列不存在返回null
     *
     * @param resultSet  resultSet
     * @param clazz      列值的数据类型
     * @param columnName 列名称
     * @return 列值
     */
    public static <T> T getColumnValue(ResultSet resultSet, Class<T> clazz, String columnName) {
        return getColumnValue(resultSet, clazz, columnName, null);
    }

    /**
     * 获取列值
     *
     * @param resultSet    resultSet
     * @param clazz        列值的数据类型
     * @param columnName   列名称
     * @param defaultValue 如果列不存在, 给个默认值
     * @return 列值
     */
    public static <T> T getColumnValue(ResultSet resultSet, Class<T> clazz, String columnName, T defaultValue) {
        try {
            Object result;
            if (Byte.class.equals(clazz)) {
                result = resultSet.getByte(columnName);
            } else if (Boolean.class.equals(clazz)) {
                result = resultSet.getBoolean(columnName);
            } else if (Short.class.equals(clazz)) {
                result = resultSet.getShort(columnName);
            } else if (Integer.class.equals(clazz)) {
                result = resultSet.getInt(columnName);
            } else if (Long.class.equals(clazz)) {
                result = resultSet.getLong(columnName);
            } else if (Float.class.equals(clazz)) {
                result = resultSet.getFloat(columnName);
            } else if (Double.class.equals(clazz)) {
                result = resultSet.getDouble(columnName);
            } else if (BigDecimal.class.equals(clazz)) {
                result = resultSet.getBigDecimal(columnName);
            } else if (String.class.equals(clazz)) {
                result = resultSet.getString(columnName);
            } else if (Array.class.equals(clazz)) {
                result = resultSet.getArray(columnName);
            } else if (Date.class.equals(clazz)) {
                result = resultSet.getDate(columnName);
            } else if (Time.class.equals(clazz)) {
                result = resultSet.getTime(columnName);
            } else if (Timestamp.class.equals(clazz)) {
                result = resultSet.getTimestamp(columnName);
            } else if (Blob.class.equals(clazz)) {
                result = resultSet.getBlob(columnName);
            } else if (Clob.class.equals(clazz)) {
                result = resultSet.getClob(columnName);
            } else if (URL.class.equals(clazz)) {
                result = resultSet.getURL(columnName);
            } else if (RowId.class.equals(clazz)) {
                result = resultSet.getRowId(columnName);
            } else if (NClob.class.equals(clazz)) {
                result = resultSet.getNClob(columnName);
            } else {
                result = resultSet.getObject(columnName);
            }
            return (T) result;
        } catch (SQLException e) {
            log.warn(e.getMessage());
            return defaultValue;
        }
    }
}