package org.keywordsFramework.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DateBaseUtil {

    private static SqlSession sqlSession;

    //建立数据库连接
    public static SqlSession getSqlSession() {

        try {
            Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }

    // 关闭连接
    public static void closeSqlSession() {
        sqlSession.close();
    }

    //删除TestModel
    public static void deleteTestModel(String[] arr) {
        try {
            sqlSession.delete("deleteTestModel",arr);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除TestModel
    public static void deleteTestMaterial(String[] arr) {
        try {
            sqlSession.delete("deleteTestMaterial",arr);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询模型ID
    public static int selectModelId(String modelName) {
        try {
            return sqlSession.selectOne("getModelId", modelName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
