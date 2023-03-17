package Lesson_5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookStoreApiTest {

    private String baseUrl = "https://demoqa.com/api/v1/books";
    private String token;

    @BeforeEach
    public void getToken() {
        // Get the authentication token
        token = given()
                .contentType("application/json")
                .body("{\"username\": \"myUsername\", \"password\": \"myPassword\"}")
                .when()
                .post("https://demoqa.com/api/v1/login")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

    @Test
    public void getBooksTest() {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(baseUrl)
                .then()
                .statusCode(200)
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    public void addBookTest() {
        String requestBody = "{\"title\": \"The Test Book\", \"author\": \"John Doe\", \"isbn\": \"1234567890\"}";

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseUrl)
                .then()
                .statusCode(201)
                .body("title", equalTo("The Test Book"))
                .body("author", equalTo("John Doe"))
                .body("isbn", equalTo("1234567890"));
    }

    @Test
    public void updateBookTest() {
        int bookId = 1;
        String requestBody = "{\"title\": \"The Updated Test Book\", \"author\": \"Jane Smith\", \"isbn\": \"0987654321\"}";

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(baseUrl + "/" + bookId)
                .then()
                .statusCode(200)
                .body("title", equalTo("The Updated Test Book"))
                .body("author", equalTo("Jane Smith"))
                .body("isbn", equalTo("0987654321"));
    }

    @Test
    public void deleteBookTest() {
        int bookId = 1;

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(baseUrl + "/" + bookId)
                .then()
                .statusCode(204);
    }
}

