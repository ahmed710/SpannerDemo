package com.example.spannerDemo.ControllersTest;

import com.example.spannerDemo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetAllUsers() {
        webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json("[{\"id\":\"PLSwKd6eYSzWm3CvCNiT\",\"name\":\"John Doe\",\"age\":25,\"favoriteDrink\":\"Coffee\",\"pets\":[\"Dog\",\"Cat\"],\"country\":\"USA\"},{\"id\":\"RU27ywiAjsxfk6KCbA5h\",\"name\":\"John Doe\",\"age\":25,\"favoriteDrink\":\"Coffee\",\"pets\":[\"Dog\",\"Cat\"],\"country\":\"USA\"},{\"id\":\"mPouad10lk2VRLDVTQYd\",\"name\":\"John Doe\",\"age\":25,\"favoriteDrink\":\"Coffee\",\"pets\":[\"Dog\",\"Cat\"],\"country\":\"USA\"},{\"id\":\"sXKmPY1qpb5gBWgGvtnj\",\"name\":\"John Doe\",\"age\":25,\"favoriteDrink\":\"Coffee\",\"pets\":[\"Dog\",\"Cat\"],\"country\":\"USA\"},{\"id\":\"y3oolSVcgIuTkeAMbblg\",\"name\":\"Ahmed Boukhari\",\"age\":22,\"favoriteDrink\":\"Coffee\",\"pets\":[\"CAT\"],\"country\":\"Tunisia\"}]\n");
    }

    @Test
    public void testUpdateUser() {
        String userId = "7jfNStIMeijaKjCqfk4L";
        User user = new User();
        user.setId(userId);
        user.setName("John Doooooo");

        webTestClient.put()
                .uri("/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json("{\"id\":\"7jfNStIMeijaKjCqfk4L\",\"name\":\"John Doooooo\"}");
    }

    @Test
    public void testDeleteUser() {
        String userId = "7jfNStIMeijaKjCqfk4L";

        webTestClient.delete()
                .uri("/users/{id}", userId)
                .exchange()
                .expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().json("{\"message\":\"User successfully deleted.\"}") ;
    }
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("Alice");

        webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isEqualTo("Alice");
    }
}
