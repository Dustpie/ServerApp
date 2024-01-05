package com.example.servermanager;

import com.example.servermanager.domain.Server;
import com.example.servermanager.keywords.Status;
import com.example.servermanager.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerManagerApplication.class, args);
    }
@Bean
    CommandLineRunner run(ServerRepo serverRepo) {
        return args -> {
            serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "32GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
            serverRepo.save(new Server(null, "192.168.1.58", "Fedora Linux", "16GB", "Dell Tower", "http://localhost:8080/server/image/server2.png", Status.SERVER_UP));
            serverRepo.save(new Server(null, "192.168.1.21", "MS 2008", "64GB", "Web Server", "http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
            serverRepo.save(new Server(null, "192.168.1.14", "Red Hat Enterprise Linux", "32GB", "Mail Server", "http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));
        };
    }
}