package com.shouguouo.tools.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shouguouo~
 * @date 2020/4/8 - 15:55
 */
public class TestDataUtil {

    public static String transferToJson(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                String columnName = resultSet.getMetaData().getColumnLabel(i);
                map.put(StringUtils.lineToHump(columnName), resultSet.getObject(columnName));
            }
            result.add(map);
        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullStringAsEmpty.getMask());
    }

}
