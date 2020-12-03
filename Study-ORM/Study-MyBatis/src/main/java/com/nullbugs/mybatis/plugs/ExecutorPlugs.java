package com.nullbugs.mybatis.plugs;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 * ParameterHandler (getParameterObject, setParameters)
 * ResultSetHandler (handleResultSets, handleOutputParameters)
 * StatementHandler (prepare, parameterize, batch, update, query)
 *
 * 可以拦截的方法只有这些
 */
@Intercepts({
        @Signature(type = Executor.class,method = "query",args ={MappedStatement.class,Object.class,RowBounds.class, ResultHandler.class} )
})
public class ExecutorPlugs implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("query方法执行前拦截");
        Object target = invocation.getTarget();
        Object[] args = invocation.getArgs();
        System.out.println();
        System.out.println(target);
        System.out.println(args);
        Object proceed = invocation.proceed();
        System.out.println(proceed);
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
