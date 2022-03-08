package com.yjxxt.test;

import com.yjxxt.pojo.Dept;
import com.yjxxt.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/*
   测试3个查询方法
    selectOne("命名空间.id")  查询一个数据,返回对应类型的一个结果,如果没有查询到返回null
    selectOne("命名空间.id",Object) 第二个参数为sql语句的入参
    selectList("命名空间.id") 查询多个数据,返回一个List集合,如果没有查询到返回空的List
    selectList("命名空间.id",Object) 第二个参数为sql语句的入参
    selectMap("命名空间.id","作为key的字段名") 查询多个数据,返回一个Map集合,如果没有查询到返回空的Map
    selectMap("命名空间.id",Object,"作为key的字段名") 第二个参数为sql语句的入参,第三个参数返回Map集合中每条数据作为key的字段名

 */
public class Class002_Method {
    public static void main(String[] args) throws IOException {
        //1.加载XML核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory实例
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //3.通过工厂获取回话SqlSession-->相当于Connection
        SqlSession session = factory.openSession();
        //4.执行指定的SQL语句
        Emp emp = session.selectOne("com.yjxxt.mappers.EmpMapper.queryEmpByNo",7369);

        List<Emp> list = session.selectList("com.yjxxt.mappers.EmpMapper.queryEmpByDeptno",30);

        Map<Integer,Emp> map =  session.selectMap("com.yjxxt.mappers.EmpMapper.queryEmpByDeptno",20,"empno");


        //5.得到结果处理结果
        //System.out.println(emp);
        //list.forEach(System.out::println);
        System.out.println(map);

        //6.关闭回话
        session.close();
    }
}
