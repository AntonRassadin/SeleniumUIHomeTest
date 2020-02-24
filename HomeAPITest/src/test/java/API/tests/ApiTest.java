package API.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiTest {
    @Test
    public void avatarsComparisonTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        List<Map<String, String>> jsoneResponce = response.jsonPath().getList("data");
        List<byte[]> avatars;
        avatars = jsoneResponce.stream()
                .map(x -> (x.get("avatar")))
                .map(x -> given().get(x).asByteArray())
                .collect(Collectors.toList());
        avatars.forEach(x->Assert.assertEquals(x, avatars.get(0), "avatars are not equals"));
    }

    @Test
    public void succesfullLoginTest(){
        Map<String, Object> data= new HashMap<>();
        data.put("email","eve.holt@reqres.in");
        data.put("password","cityslicka");
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        String token = response.jsonPath().get("token");
        Assert.assertEquals(token,"QpwL5tke4Pnpja7X4","Login is not successful");
    }

    @Test
    public void unSuccesfullLoginTest(){
        Map<String, Object> data= new HashMap<>();
        data.put("email","peter@klaven");

        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Response response = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(400)
                .extract()
                .response();
        String error = response.jsonPath().get("error");
        Assert.assertEquals(error,"Missing password","Message: " + error);
    }

    @Test
    public void dataListSortedTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        List<Map<String, Integer>> jsoneResponce = response.jsonPath().getList("data");
        List<Integer> list = jsoneResponce.stream().map(x->x.get("year")).collect(Collectors.toList());

        System.out.println(list);
        System.out.println(list.stream().sorted().collect(Collectors.toList()));

        Assert.assertEquals(list, list.stream().sorted().collect(Collectors.toList()), "Years value are not in ascending order");

    }

}
