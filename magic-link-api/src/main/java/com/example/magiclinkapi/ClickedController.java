package com.example.magiclinkapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clicked")
public class ClickedController {

  @PostMapping
  public ResponseEntity<Object> clicked() {
    return ResponseEntity.ok().build();
  }

}
