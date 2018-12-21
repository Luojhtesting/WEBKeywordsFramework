import org.apache.ibatis.session.SqlSession;

import org.keywordsFramework.util.DateBaseUtil;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class DateTest {

    public static void main(String[] args) {
        String[] modelNameArray = {"TestModelCyan","TestModelCyanUpdate"};
        DateBaseUtil.getSqlSession();
        DateBaseUtil.deleteTestModel(modelNameArray);
        System.out.println(DateBaseUtil.selectModelId("TestModel1"));
        DateBaseUtil.closeSqlSession();




    }
}
