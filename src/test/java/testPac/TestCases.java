package testPac;

import TrelloElement.*;
import org.testng.annotations.Test;

public class TestCases extends TrelloParam {
    private  final TrelloBoard board;
    private  final TrelloList list;
    private final TrelloCard card ;
    private final CommentCard comment ;
    private final TrelloSearch search;
    private final InvalidKeys invalid;



    public TestCases() {
        board= new TrelloBoard();
        list=new TrelloList();
        card =new TrelloCard();
        comment=new CommentCard();
        search=new TrelloSearch();
        invalid=new InvalidKeys();

    }
    @Test
    public void createBoard() {
        board.createBoard("board");
        board.getBoard();
    }

    @Test(dependsOnMethods = "createBoard",priority = 1)
    public void updateBoard() {
        board.UpdateBoard("updeted Board");
        board.getBoard();
    }
    @Test(dependsOnMethods = "createBoard",priority = 12)
    public void deleteBoard() {
        board.getBoard();
        board.deleteBoard();
    }

    @Test(dependsOnMethods = "createBoard",priority = 2)
    public void createList() {
        list.createList("list");

    }
    @Test(dependsOnMethods = "createList",priority = 3)
    public void updateList() {
        list.createList("List");
        list.updateList("updated name");
        list.getList();
    }
    @Test(dependsOnMethods = "createList",priority = 4)
    public void createCard() {
        card.createCard("card1");
        }
    @Test(dependsOnMethods = "createCard",priority = 6)
    public void createComment() {
        card.createCard("card3");
        comment.createCommentOnCard("Type ur Comment");
    }
    @Test(dependsOnMethods = "createComment",priority = 8)
    public void updateComment() {
            comment.UpdateComment("Retype ur text");
        }
    @Test(dependsOnMethods = "createComment",priority = 9)
    public void deleteComment() {
        comment.UpdateComment("Retype ur text");
        comment.deleteComment();

    }
    @Test(dependsOnMethods = "createCard",priority = 5)
    public void updateCard() {
        card.getCard();
        card.UpdateCard("updated card");
    }
    @Test(priority = 11)
    public void deleteCard() {
        card.getCard();
        card.deleteCard();
    }
    @Test(dependsOnMethods = "createComment",priority = 7 )
    public void moveListToAnotherBoard() {
        card.createCard("card1");
        card.createCard("card2");
        card.createCard("card3");
        board.createBoard("After");
        list.moveList();
    }
    @Test(dependsOnMethods = "createBoard",priority = 10)
    public void searchByQuery() {
       search.search("BoardName");
    }
    @Test(priority = 14)
    public void testInvalidTokenAndKey() {
        invalid.testInvalid();
    }



}
