package com.echoinacup.mix;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mixes")
public class MixController {

    @GetMapping
    public ResponseEntity<Object> getMixes() {
        return ResponseEntity.ok().body(List.of("mix1", "mix2"));
    }
}
