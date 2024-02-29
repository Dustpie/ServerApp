package com.example.servermanager.resource;

import com.example.servermanager.domain.Response;
import com.example.servermanager.domain.Server;
import com.example.servermanager.keywords.Status;
import com.example.servermanager.service.implementation.ServerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

/**
 * This class is used to create a REST API for the server manager application.
 * It contains methods that can be used to create, retrieve, update and delete servers.
 * It also contains methods that can be used to ping servers and retrieve images from the server.
 */
@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServiceResource {
    private final ServerServiceImpl serverService; // We declare an instance of the serverServiceImplementation

    /**
     * Retrieves a list of servers with a limit on the number of servers returned.
     *
     * @param limit The maximum number of servers to return.
     * @return A collection of servers.
     */
    @GetMapping("/list")
    public ResponseEntity<Response> getServers(@RequestParam(required = false) Integer limit) throws InterruptedException { // We use the @RequestParam annotation to retrieve the limit parameter from the request and Integer to convert it to an integer.
        TimeUnit.SECONDS.sleep(3); // We use the TimeUnit class to sleep the thread for 2 seconds.
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.serverList(limit)))
                        .message("Servers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    /**
     * Pings a server with the given IP address.
     *
     * @param ipAddress The IP address of the server to be pinged.
     * @return The server that was pinged.
     */
    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    /**
     * Creates a new server.
     *
     * @param server The server to be created.
     * @return The created server.
     */
    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.create(server)))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    /**
     * Retrieves a server with the given ID.
     *
     * @param id The ID of the server to retrieve.
     * @return The server.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        Server server = serverService.get(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.get(id)))
                        .message("Server retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    /**
     * Deletes a server with the given ID.
     *
     * @param id The ID of the server to delete.
     * @return The server that was deleted.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        Server server = serverService.get(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", serverService.delete(id)))
                        .message("Server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    /**
     * Retrieves an image from the server by accessing
     * the file system and reading out the bytes of the image.
     * The image is then returned as a byte array.
     *
     * @param imageName The name of the image to retrieve.
     * @return The image.
     * @throws IOException
     */
    @GetMapping(path = "image/{imageName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + imageName));
    }
}
