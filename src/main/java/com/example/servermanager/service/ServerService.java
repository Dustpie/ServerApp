package com.example.servermanager.service;

import com.example.servermanager.domain.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

/**
 * This interface provides the contract for creating and managing servers.
 */
public interface ServerService {
    /**
     * Creates a new server.
     *
     * @param server The server to be created.
     * @return The created server.
     */
    Server create(Server server);
    /**
     * Pings a server with the given IP address.
     *
     * @param ipAddress The IP address of the server to be pinged.
     * @return The server that was pinged.
     */
    Server ping(String ipAddress) throws IOException;
    /**
     * Retrieves a list of servers with a limit on the number of servers returned.
     *
     * @param limit The maximum number of servers to return.
     * @return A collection of servers.
     */
    Collection<Server> serverList(int limit);
    /**
     * Retrieves a server with the given ID.
     *
     * @param id The ID of the server to retrieve.
     * @return The server with the given ID.
     */
    Server get(long id);
    /**
     * Updates a server.
     *
     * @param server The server to be updated.
     * @return The updated server.
     */
    Server update(Server server);
    /**
     * Deletes a server with the given ID.
     *
     * @param id The ID of the server to delete.
     * @return A boolean indicating whether the deletion was successful.
     */
    Boolean delete(Long id);

}
