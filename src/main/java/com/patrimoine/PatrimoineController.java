package com.patrimoine;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {
    private final Path storagePath = Paths.get("patrimoines");

    public PatrimoineController() throws IOException {
        if (!Files.exists(storagePath)){
            Files.createDirectories(storagePath);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrimoine> getPatrimoine(@PathVariable String id) {
        Path filePath = storagePath.resolve(id + ".json");
        if (Files.exists(filePath)){
            return ResponseEntity.notFound().build();
        }
        try {
            String json = Files.readString(filePath);
            Patrimoine patrimoine = Patrimoine.fromJson(json);
            return ResponseEntity.ok(patrimoine);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrimoine> putPatrimoine(@PathVariable String id, @RequestBody Patrimoine patrimoine) {
        patrimoine.setDerniereModification(LocalDateTime.now());
        Path filePath = storagePath.resolve(id + ".json");
        try{
            String json = patrimoine.toJson();
            Files.writeString(filePath, json);
            return ResponseEntity.ok(patrimoine);
        }catch (IOException e){
            return ResponseEntity.status(500).build();
        }
    }

}
