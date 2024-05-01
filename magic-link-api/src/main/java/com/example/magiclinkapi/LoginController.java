package com.example.magiclinkapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

  @Autowired
  private MagicLinkService magicLinkService;

  @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<Object> login(@RequestBody MultiValueMap<String, String> formData) {
    String email = formData.getFirst("email");

    if (magicLinkService.isUserConfirmed(email)) {
      return ResponseEntity.noContent().header("HX-Redirect", "/secured.html").build();
    } else {
      return ResponseEntity.noContent().header("HX-Redirect", "/unconfirmed.html").build();
    }

  }

}
