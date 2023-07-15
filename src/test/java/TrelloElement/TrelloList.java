package TrelloElement;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TrelloList  extends TrelloParam {


    protected static String  listId,boardId;


    public void getList() {
        Response res = given().spec(requestSpec)
                .pathParam("id", listId).queryParams(keys).log().all()
                .header(accept).
                when()
                .get("lists/{id}");
        res.then().log().all();
        String boardName = res.getBody().jsonPath().get("name");
        System.out.println(boardName.toUpperCase());

    }



    public void createList(String name) {
        Response res =
                given().spec(requestSpec).queryParams(keys).
                        queryParam("name", name)
                        .pathParam("id",TrelloBoard.boardId)
                        .contentType(ContentType.JSON)
                        .log().all().header(accept).
                        post("boards/{id}/lists");
        res.then().statusCode(200);
        listId=  res.body().jsonPath().get("id");
        System.out.println(listId+"id=");
    }
    public void updateList(String name) {
        System.out.println(listId);
        Response res =
                given().spec(requestSpec).pathParam("id", listId)
                        .queryParams(keys).queryParam("name", name).log().all()
                        .header(accept).put("lists/{id}");
        res.then().assertThat().statusCode(200).log().all();
       String updatedName =res.body().jsonPath().get("id");
        System.out.println(updatedName);

    }
    public void moveCardsToList() {
        Response res =
                given().spec(requestSpec).
                        pathParam("id", listId).
                        queryParam("idBoard",TrelloBoard.boardId).
                        queryParams(keys).queryParam("name", "RestAssured").log().all()
                        .header(accept).post("lists/{id}/moveAllCards");
        res.then().assertThat().statusCode(200).log().all();


    }
    public void moveList() {
        Response res =
                given().spec(requestSpec).
                        pathParam("id", listId).
                        queryParam("value",TrelloBoard.boardId).
                        queryParams(keys).queryParam("name", "RestAssured").log().all()
                        .header(accept).put("lists/{id}/idBoard");
        res.then().assertThat().statusCode(200).log().all();


    }

}

