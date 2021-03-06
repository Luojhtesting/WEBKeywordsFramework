package org.keywordsFramework.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class DataBaseUtil {

    private static SqlSession sqlSession;

    //建立数据库连接
    public static void connectionSqlSession() {

        try {
            Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    //根据创建用户，删除goods表数据
    public static void deleteGoodsByCreaterUser(String userName) {
        try {
            sqlSession.delete("deleteGoodsData", userName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据创建用户，删除Material表数据
    public static void deleteMaterialByCreaterUser(String userName) {
        try {
            sqlSession.delete("deleteMaterialData", userName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除用户反馈测试数据
    public static void deleteFeedbackData(int uid) {
        try {
            sqlSession.delete("deleteFeedbackData", uid);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除品牌测试数据
    public static void deleteBrandsData(String[] arr) {
        try {
            sqlSession.delete("deleteBrandsData", arr);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除贴图商品数据
    public static void deleteMapGoodsData(String userName) {
        try {
            sqlSession.delete("deleteMapGoodsData", userName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除账号版本配置数据
    public static void deleteUserVersionConfigData(String userName) {
        try {
            sqlSession.delete("deleteUserVersionConfigData", userName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除角色数据
    public static void deleteRoleData(String userName) {
        try {
            sqlSession.delete("deleteRoleData", userName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
