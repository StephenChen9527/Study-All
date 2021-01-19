package com.nullbugs.mybatis.handler;

import com.alibaba.fastjson.JSONObject;
import com.nullbugs.mybatis.pojo.Desci;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonHandler implements TypeHandler<Desci> {


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Desci desci, JdbcType jdbcType) throws SQLException {
        System.out.println("设置参数");
        preparedStatement.setString(i,JSONObject.toJSONString(desci));
    }

    @Override
    public Desci getResult(ResultSet resultSet, String s) throws SQLException {
        System.out.println("getResult1");
        String string = resultSet.getString(s);
        if(string!=null && !string.trim().equals("")){
            return JSONObject.parseObject(string, Desci.class);
        }

        return null;
    }

    @Override
    public Desci getResult(ResultSet resultSet, int i) throws SQLException {
        System.out.println("getResult2");
        String str = resultSet.getString(i);
        if(str!=null && !str.trim().equals("")){
            return JSONObject.parseObject(str, Desci.class);
        }
        
        return null;
    }

    @Override
    public Desci getResult(CallableStatement callableStatement, int i) throws SQLException {
        System.out.println("callableStatement");
        String str = callableStatement.getString(i);
        if(str!=null && !str.trim().equals("")){
            return JSONObject.parseObject(str, Desci.class);
        }

        return null;
    }
}
