package org.keywordsFramework.util;

import io.restassured.response.Response;
import org.keywordsFramework.configuration.Constans;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class InterfaceAction {

    //获取超级后台token
    public static String getBackstageToken() {
        String json = "{\"password\":\"to8to123\",\"user_name\":\"admin.tumax\"}";
        Response response = given()
            .contentType("application/json")
            .body(json)
        .when()
            .post(Constans.Backstage_Login_Path)
        .then()
            .statusCode(200)
        .extract()
            .response()
        ;

        String token = response.path("data.token");
        return token;
    }

    //获取渲染状态
    public static int getModelAppleState(int modelId) {
        String json = "{\"id\":" + modelId + "}";
        Response response = given()
            .contentType("application/json")
            .header("TOKEN",getBackstageToken())
            .body(json)
        .when()
            .post(Constans.Backstage_Model_API_Path)
        .then()
            .statusCode(200)
            .extract()
        .response()
        ;

        ArrayList List = response.path("data.goods_attributes.model.made_status");
        String s = List.get(0).toString();
        int i = Integer.valueOf(s);
        return i;
    }

    //获取编辑器auth
    public static String getAuth(int uid) {
        String json = "{\"uid\":" + uid + ",\"sub_uid\":0,\"role\":2}";

        Response response = given()
            .contentType("application/json")
            .body(json)
        .when()
            .post("http://tumaxapi.to8to.com/generatorAuth")
            //.prettyPeek()
        .then()
            .statusCode(200)
        .extract()
            .response()
        ;

        return response.path("data.auth");
    }

    //创建用户反馈接口
    public static boolean createFeedbackResult(String auth) {
        String Json = "{\"question_from\":1,\"content\":\"Test\",\"flash_number\":\"1.8.0\"}";

        Response response = given()
            .contentType("application/json")
            .body(Json)
        .when()
            .post("http://tumaxflashapi.to8to.com/api/feedback?auth={auth}", auth)
            //.prettyPeek()
        .then()
            .statusCode(200)
        .extract()
            .response()
        ;

        int i = response.path("code");

        if (i == 0) {
            return true;
        } else {
            return false;
        }
    }
}
