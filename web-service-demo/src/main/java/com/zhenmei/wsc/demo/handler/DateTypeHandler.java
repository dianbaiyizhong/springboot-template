package com.zhenmei.wsc.demo.handler;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.TIMESTAMP)
@MappedTypes(Object.class)
public class DateTypeHandler implements TypeHandler<String> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {

    }

    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getDate(columnName).toString();
    }

    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        long time = rs.getLong(columnIndex);
        return rs.getDate(columnIndex).toString();
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getDate(i).toString();
    }


}