package com.nullbugs.mybatis.plugs;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;


@Intercepts({
        @Signature(type = ResultSetHandler.class,method = "handleResultSets",args = Statement.class)
})
public class ResultSetPlug implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("resultset方法拦截");
        Object proceed = invocation.proceed();
        Object target = invocation.getTarget();
        Object[] args = invocation.getArgs();
        System.out.println(proceed);
        System.out.println(target);
        System.out.println(args);
        return proceed;
    }



    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
