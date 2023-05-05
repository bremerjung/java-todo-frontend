package de.neuefische.backend.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateUUID {
    String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
