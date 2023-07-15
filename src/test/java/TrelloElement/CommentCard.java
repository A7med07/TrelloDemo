package TrelloElement;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CommentCard extends TrelloParam {

        private static String  commentId;




        public void createCommentOnCard(String text) {
            Response res =
                    RestAssured.given().spec(requestSpec).queryParams(keys).
                            queryParam("text", text)
                            .pathParam("id", TrelloCard.cardId)
                            .contentType(ContentType.JSON)
                            .log().all().header(accept).post("cards/{id}/actions/comments");
            res.then().statusCode(200);
           res.then().log().all();
          commentId= res.body().jsonPath().get("id");
        }
        public void UpdateComment(String text) {
            System.out.println(TrelloList.listId);
            Response res =
                    RestAssured.given().spec(requestSpec).pathParam("id", TrelloCard.cardId)
                            .pathParam("idAction",commentId)
                            .queryParams(keys).queryParam("text",text).log().all()
                            .header(accept).put("cards/{id}/actions/{idAction}/comments");
            res.then().assertThat().statusCode(200).log().all();

        }
        public void deleteComment() {
            Response res =
                    RestAssured.given().spec(requestSpec).pathParam("idAction",commentId).pathParam("id", TrelloCard.cardId).
                            queryParams(keys).log().all()
                            .header(accept).delete("cards/{id}/actions/{idAction}/comments");
            res.then().assertThat().statusCode(200).log().all();


        }

    }


