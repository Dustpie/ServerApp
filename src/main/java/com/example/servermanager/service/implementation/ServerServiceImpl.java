package com.example.servermanager.service.implementation;
import com.example.servermanager.domain.Server;
import com.example.servermanager.keywords.Status;
import com.example.servermanager.repository.ServerRepo;
import com.example.servermanager.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo; // serverRepo is used as an argument in the constructor to create an instance of ServerRepo in the ServerServiceImpl class

    /**
     * Creates a new server.
     *
     * @param server The server to be created.
     * @return The created server.
     */
    @Override
    public Server create(Server server) {
        log.info("Pinging server IP: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    /**
     * Pings a server with the given IP address.
     *
     * @param ipAddress The IP address of the server to be pinged.
     * @return The server that was pinged.
     */
    @Override
    public Server ping(String ipAddress) throws IOException {
        Server server = serverRepo.findByIpAddress(ipAddress);
        log.info("Saving new server: {}", ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus((address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN));
        serverRepo.save(server);
        return server;
    }

    /**
     * Retrieves a list of servers with a limit on the number of servers returned.
     *
     * @param limit The maximum number of servers to return.
     * @return A collection of servers.
     */
    @Override
    public Collection<Server> serverList(int limit) {
            log.info("Fetching all  servers");
            return serverRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    /**
     * Retrieves a server with the given ID.
     *
     * @param id The ID of the server to retrieve.
     * @return The server with the given ID.
     */
    @Override
    public Server get(long id) {
        log.info("Fetching server by id : {}", id);
        return serverRepo.findById(id).get();
    }

    /**
     * Updates a server.
     *
     * @param server The server to be updated.
     * @return The updated server.
     */
    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepo.save(server);
    }

    /**
     * Deletes a server with the given ID.
     *
     * @param id The ID of the server to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server: {}", id);
        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}
