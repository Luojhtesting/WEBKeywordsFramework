import io.restassured.response.Response;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.jetty.websocket.api.Session;
import org.keywordsFramework.configuration.Constans;
import org.keywordsFramework.util.DateBaseUtil;
import org.keywordsFramework.util.InterfaceAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.keywordsFramework.util.KeyBoardUtil.*;

public class TestCase {
    public static void main(String[] args) throws InterruptedException {
        DateBaseUtil.getSqlSession();
        System.out.println(DateBaseUtil.selectModelId("文强的测试呀"));
        getModelAppleState(DateBaseUtil.selectModelId("TestModel1"));
        DateBaseUtil.closeSqlSession();






    }

    public static void getModelAppleState(int modelId) {
        String json = "{\"id\":" + modelId + "}";
        Response response = given()
                .contentType("application/json")
                .header("TOKEN","t8t3d_admin_user_token_5096d0368fef3ff4205cc23b11f370e6")
                .body(json)
                .when()
                .post(Constans.Backstage_Model_API_Path)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                ;

        ArrayList List = response.path("data.goods_attributes.model.made_status");
        System.out.println(List);
//        String s = List.get(0).toString();
//        int i = Integer.valueOf(s);
//        return i;

    }



}
