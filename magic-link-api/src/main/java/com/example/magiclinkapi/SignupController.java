package com.example.magiclinkapi;

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
@RequestMapping(value = "/api/signup")
public class SignupController {
  private final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);

  @Autowired
  private MagicLinkService magicLinkService;

  @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<Object> signup(@RequestBody MultiValueMap<String, String> formData) {
    String email = formData.getFirst("email");
    LOGGER.info("Signup request received for email: {}", email);

    UserEntity userEntity = magicLinkService.saveUser(email);

    // TODO to a service
    String magicLink = String.format("/api/signup/%s/confirm", userEntity.getUserId().toString());

    String response = String.format(
        """
            <div>
              <p>Thank you for signing up!</p>
              <p>Here is the confirmation link that would be sent via email: <a data-cy="confirmationLink" href="%s">The Confirmation Link</a></p>
            </div>
            """,
        magicLink);

    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/{userId}/confirm")
  public RedirectView confirm(@PathVariable String userId) {
    LOGGER.info("Confirmation request received for userId: {}", userId);

    magicLinkService.confirmUser(userId);

    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("/confirmation.html");

    return redirectView;
  }

}
