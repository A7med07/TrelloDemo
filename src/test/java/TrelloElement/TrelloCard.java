package TrelloElement;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class TrelloCard extends TrelloParam {

    protected static String  cardId;


    public void getCard() {
        Response res =  given().spec(requestSpec)
                .pathParam("id", cardId).queryParams(keys).log().all()
                .header(accept).
                when()
                .get("cards/{id}");
        String boardName = res.getBody().jsonPath().get("name");
        res.then().log().all();
        System.out.println(boardName.toUpperCase());


    }

    public void createCard(String name) {
        Response res =
                given().spec(requestSpec).queryParams(keys).
                        queryParam("name", name)
                        .pathParam("id",TrelloList.listId).body("{\"name\":\""+name+"\"}")
                        .contentType(ContentType.JSON)
                        .log().all().header(accept).post("lists/{id}/cards");
        res.then().statusCode(200);
        cardId=  res.body().jsonPath().get("id");
        System.out.println("id="+cardId);
    }
    public void UpdateCard(String name) {
        System.out.println(TrelloList.listId);
        Response res =
                given().spec(requestSpec).pathParam("id", cardId)
                        .queryParams(keys).queryParam("name",  name).log().all()
                        .header(accept).put("cards/{id}");
        res.then().assertThat().statusCode(200).log().all();

    }
    public void deleteCard() {
        Response res =
                 given().spec(requestSpec).pathParam("id", cardId).
                        queryParams(keys).log().all()
                        .header(accept).delete("cards/{id}");
        res.then().assertThat().statusCode(200).log().all();


    }


}
