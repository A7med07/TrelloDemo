package TrelloElement;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TrelloSearch extends TrelloParam {
    @Test
    public void search(String query) {
        Response res =
               given().spec(requestSpec).queryParams(keys).
                        queryParam("query", query)
                        .contentType(ContentType.JSON)
                        .log().all().header(accept).get("search");
        res.then().statusCode(200);
      res.then().log().all();
    }
}
