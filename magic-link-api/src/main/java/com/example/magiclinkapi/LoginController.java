package com.example.magiclinkapi;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {
  private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  private MagicLinkService magicLinkService;

  @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<Object> login(@RequestBody MultiValueMap<String, String> formData) {
    LOGGER.info("Login request received for email: {}", formData.getFirst("email"));

    String email = formData.getFirst("email");

    try {
      String magicLink = magicLinkService.preAuthenticate(email);

      String response = String.format(
          """
              <div>
                <p>Thank you for logging in!</p>
                <p>Here is the magic link that would be sent via email: <a data-cy="magicLink" href="%s">The Magic Link</a></p>
              </div>
              """,
          magicLink);

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.ok("""
          <div>
            <p data-cy="refusalMessage">Sorry, we could not log you in.</p>
          </div>
          """);
    }
  }

  @GetMapping("/{magicUUID}")
  public RedirectView login(@PathVariable String magicUUID) {
    LOGGER.info("Magic link request received for magicUUID: {}", magicUUID);

    if (magicLinkService.authenticate(UUID.fromString(magicUUID))) {
      return new RedirectView("/secured.html");
    } else {
      return new RedirectView("/error.html");
    }
  }

}
