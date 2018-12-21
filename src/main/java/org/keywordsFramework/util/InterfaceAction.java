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
}
