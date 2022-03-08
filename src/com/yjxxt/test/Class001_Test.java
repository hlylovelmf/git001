package com.yjxxt.test;

import com.yjxxt.pojo.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*
    Mybatis 基于持久层的orm框架
        对原生JDBC的封装
        半自动化框架
        学习成本低,使用简单,适合做一些业务多变的互联网项目

    环境搭建:
     1)下载资源jar包
     2)项目下新建lib路径,需要的jar包放入lib路径下,选中右键add as lib..
     3)编写代码程序
        1.核心配置文件 : 做mybatis使用的核心基本配置
                        数据库的连接信息
                        是否使用连接池技术
                        SQL映射文件的扫描
                        ----
        2.SQL映射文件 : 定义SQL语句
        3.测试执行使用mybatis框架连接数据库,执行SQL语句,得到结果
 */
public class Class001_Test {
    public static void main(String[] args) throws IOException {
        //1.加载XML核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory实例
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //3.通过工厂获取回话SqlSession-->相当于Connection
        SqlSession session = factory.openSession();
        //4.执行指定的SQL语句
        //selectList("命名空间.id")
        List<Dept> list = session.selectList("com.yjxxt.mappers.DeptMapper.queryAll");
        //5.得到结果处理结果
        list.forEach(System.out::println);
        //6.关闭回话
        session.close();
    }
}
