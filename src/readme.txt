# Welcome to my RestAssured Automation Project!

This is a demo project for automating tests on the Trello website using RESTful APIs. With this project, you can create boards, cards, lists, comments, and more programmatically.

## Getting Started

To get started with this project:

1. Clone this repository to your local machine.
2. Install all necessary dependencies (e.g., Java JDK).
3. Open the project in your preferred IDE.

## Usage

You can use this automation framework to perform various actions on Trello API such as creating new boards, adding cards or lists, leaving comments, and much more.

Here's an example of how to create a new board:

```java
@Test
public void testCreateBoard() {
    String boardName = "My New Board";
  
    given()
        .baseUri("https://api.trello.com")
        .queryParam("key", "your_api_key")
        .queryParam("token", "your_api_token")
        .queryParam("name", boardName)
    .when()
        .post("/1/boards/")
    .then()
        .statusCode(200)
        // Add additional assertions if needed
}