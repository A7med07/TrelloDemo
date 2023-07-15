package TrelloElement;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class InvalidKeys extends TrelloParam {
    @Test
    public void testInvalid() {
        Response res =
                given().spec(requestSpec).
                        queryParams("key","incorrectKey","token","incorrectToken").
                        queryParam("name", "RestAssured")
                        .contentType(ContentType.JSON).body("{\"name\":\""+"invalid"+"\"}")
                        .log().all().header(accept).post("boards");
        res.then().log().all().statusCode(401);
        res.then().assertThat().body(equalTo("invalid key"));

    }
}
