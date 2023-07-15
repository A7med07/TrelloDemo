package TrelloElement;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;

public class TrelloParam {
    protected static RequestSpecification requestSpec;
    protected  static HashMap keys;
    protected  static Header accept;

    @BeforeClass
    public static void Req_Specif() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://api.trello.com/1/").
                build();
    }

    @BeforeClass
    public  static void setKeys() {

        keys = new HashMap<>();
        keys.put("key", ":)");
        keys.put("token",
                ":)");
        accept = new Header("Accept", "application/json");

    }
}
