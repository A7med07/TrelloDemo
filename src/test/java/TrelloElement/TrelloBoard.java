package TrelloElement;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class TrelloBoard extends TrelloParam {
    protected static String boardId;


    public void getBoard() {
        Response res = given().spec(requestSpec)
                .pathParam("id", boardId).queryParams(keys).log().all()
                .header(accept).
                when()
                .get("boards/{id}");
        String boardName = res.getBody().jsonPath().get("name");
        System.out.println(boardName.toUpperCase());

    }
    public void createBoard(String name) {
        Response res =
                given().spec(requestSpec).queryParams(keys).
                        queryParam("name", "RestAssured")
                        .contentType(ContentType.JSON).body("{\"name\":\""+name+"\"}")
                        .log().all().header(accept).post("boards");
        res.then().statusCode(200);
       boardId=  res.body().jsonPath().get("id");
        System.out.println(boardId+"id=");
    }
    public void UpdateBoard(String name) {
        System.out.println(boardId);
        Response res =
                given().spec(requestSpec).pathParam("id", boardId)
                        .queryParams(keys).queryParam("name", name).log().all()
                        .header(accept).put("boards/{id}");
        res.then().assertThat().statusCode(200).log().all();

    }
    public void deleteBoard() {
        Response res =
                given().spec(requestSpec).pathParam("id", boardId).
                        queryParams(keys).queryParam("name", "RestAssured").log().all()
                        .header(accept).delete("boards/{id}");
        res.then().assertThat().statusCode(200).log().all();


    }
}