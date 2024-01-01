package com.example.servermanager.repository;

import com.example.servermanager.domain.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> {
}
