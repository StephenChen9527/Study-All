package com.nullbugs.mybatis.javaconfig;

import com.alibaba.fastjson.JSONObject;
import com.nullbugs.mybatis.mapper.PersonMapper;
import com.nullbugs.mybatis.pojo.Desci;
import com.nullbugs.mybatis.pojo.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;

public class MyBatisConfig {

    SqlSession session;

    /**
     * 通过解析XML文件，将各种信息配置出来，生成一个SqlSession
     *
     * sqlSession 是 defaultSqlSession
     *
     * defaultSession里面包含了
     *
     */
    @Before
    public void init()throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        session= sessionFactory.openSession();
    }


    @Test
    public void testQuery()  {
        // 方法1 这里需要命名空间与接口一致，这样可以动态生成子类，
        PersonMapper mapper = session.getMapper(PersonMapper.class);
        Person o = mapper.selectPerson(20);
        //方法2 通过 namespace+id 调用
        //Person o = session.selectOne("com.nullbugs.mybatis.mapper.PersonMapper.selectPerson", 1);
        System.out.println(o);
    }

    @Test
    public void testSave(){

        Person o = new Person();
        o.setAge(30);
        o.setName("xiaowang");
        o.setSex("nv");
        o.setPwd("pwd2");
        Desci desci = new Desci();
        desci.setPhone_number("150");
        desci.setPhone("150");
        desci.setDes("150");
        o.setDesci(desci);
        o.setMy("asdasdas");
        PersonMapper mapper = session.getMapper(PersonMapper.class);
        int i = mapper.savePerson(o);
        System.out.println(i);
        session.commit();
    }

}
