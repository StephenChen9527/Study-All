package com.nullbugs.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassWordByteHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if(parameter!=null && !"".equals(parameter.trim())){
            ps.setString(i,new StringBuilder(parameter).reverse().toString());
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new StringBuilder(rs.getString(columnName)).reverse().toString();
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new StringBuilder(rs.getString(columnIndex)).reverse().toString();
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new StringBuilder(cs.getString(columnIndex)).reverse().toString();
    }
}
