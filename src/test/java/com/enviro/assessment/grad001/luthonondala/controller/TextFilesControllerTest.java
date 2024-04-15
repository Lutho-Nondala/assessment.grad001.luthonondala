package com.enviro.assessment.grad001.luthonondala.controller;

import com.enviro.assessment.grad001.luthonondala.entity.TextFiles;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class TextFilesControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TextFilesController CONTROLLER;
    @Autowired
    private TestRestTemplate restTemplate;
    private  String baseUrl;
    private MockMultipartFile a;

    @BeforeEach
    void setUp() {
        assertNotNull(CONTROLLER);

            this.a = new MockMultipartFile("Foo.txt", "Foo.txt", "text/plain", "Enviro Assessment".getBytes());

        this.baseUrl = "http://localhost:" + this.port + "/enviro/";
    }

    //This method does not allow me to send a MultipartFile. Wants me to send type byte[]
    //See last test Method
    @Test
    @org.junit.jupiter.api.Order(1)
    void create() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body= new LinkedMultiValueMap<>();
        body.add("text", this.a.getBytes());

        HttpEntity<?> entity = new HttpEntity<MultiValueMap<String, Object>>(body,headers);

        String url = baseUrl + "create";
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, entity, String.class);
        log.info("Response: {}", response.getBody());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }

    //Even when I do add data to the database using Postman, this test method still does not want to work.
    //Even if the test methods are right they will not reach the database
    //Postman tests work. The problem is now more confusing more than ever.
    @Test
    @org.junit.jupiter.api.Order(2)
    void read() {
        String url = baseUrl + "read/1";
        ResponseEntity<?> response = this.restTemplate.getForEntity(url, Byte.class);
        log.info(response.toString());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }

    //Faces the same problem as the 1st test method of not wanting to send MultipartFile.
    @Test
    @org.junit.jupiter.api.Order(3)
    void update() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body= new LinkedMultiValueMap<>();
        body.add("text", this.a.getBytes());
        body.add("text_id", 1);

        HttpEntity<?> entity = new HttpEntity<MultiValueMap<String, Object>>(body,headers);

        String url = baseUrl + "update";
        this.restTemplate.put(url, entity, String.class);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void delete() {
        String url = baseUrl + "delete/1";
        this.restTemplate.delete(url);
    }

    //This test method says that it works but, it really doesn't.
    //Same problem as the read and delete. These methods will not access the database even if they have no faults
    //Currently confused
    //All Postman tests work
    @Test
    @org.junit.jupiter.api.Order(5)
    void createTest() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body= new LinkedMultiValueMap<>();
        body.add("text", this.a.getBytes());
        body.add("type", this.a.getContentType());
        body.add("name", this.a.getName());

        HttpEntity<?> entity = new HttpEntity<MultiValueMap<String, Object>>(body,headers);

        String url = baseUrl + "createTest";
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, entity, String.class);
        log.info("Response: {}", response.getBody());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }
}