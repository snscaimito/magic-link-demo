package com.example.magiclinkapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clicked")
public class ClickedController {

  private int count = 0;

  @PostMapping
  public ResponseEntity<Object> clicked() {
    String content = """
        <button hx-post="/api/clicked" hx-swap="outerHTML">
          %s times. Click me again!
        </button>
        """;
    return ResponseEntity.ok(String.format(content, ++count));
  }

}
