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
import java.util.*;

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

    @Test
    public void test1(){
        System.out.println(UUID.randomUUID().toString().replace("-","").length());
    }

    @Test
    public void batchSaveStu(){

        Random ran = new Random();
        for (int j = 0; j < 50; j++) {
            List<Map<String,Object>> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Map<String,Object> map = new HashMap<>();
                String uuid = UUID.randomUUID().toString().replace("-", "");
                map.put("name",uuid.substring(0,6));
                map.put("sex",ran.nextInt()%2==0?"X":"Y");
                map.put("age", ran.nextInt(15)+10);
                map.put("address",uuid.substring(6,16));
                map.put("pwd",uuid.substring(17,27));
                list.add(map);
            }
            session.insert("com.nullbugs.mybatis.mapper.StudentMapper.batchSaveStu",list);
            session.commit();

        }

        session.close();

    }

    @Test
    public void insertIntoData(){
        Map<String,Object> map = new HashMap<>();

        for (int i = 0; i < 10000; i++) {
            try {
                String key1 = UUID.randomUUID().toString().replace("-","");
                int key2 = new Random().nextInt(10000000);
                String key3 = UUID.randomUUID().toString();
                String key_part1 = UUID.randomUUID().toString().replace("-","").substring(0,12);
                String key_part2 = UUID.randomUUID().toString().replace("-","").substring(0,12);
                String key_part3 = UUID.randomUUID().toString().replace("-","").substring(0,12);
                String common_field = UUID.randomUUID().toString().replace("-","").substring(0,12);
                map.put("key1",key1);
                map.put("key2",key2);
                map.put("key3",key3);
                map.put("key_part1",key_part1);
                map.put("key_part2",key_part2);
                map.put("key_part3",key_part3);
                map.put("common_field",common_field);
                session.insert("com.nullbugs.mybatis.mapper.DataInsertMapper.insertData",map);
                session.commit();
                System.out.println("success:"+i);
            } catch (Exception e) {
                System.out.println("失败");
                e.printStackTrace();
            }
        }
    }

}
