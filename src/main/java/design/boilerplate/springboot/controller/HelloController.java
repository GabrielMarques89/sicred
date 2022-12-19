package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class HelloController {

  private final UserService userService;

  @GetMapping("/hello")
  public ResponseEntity<String> sayHello() {

    var result = userService.findByUsername("teste");
    return ResponseEntity.ok("Hello Spring Boot Boilerplate");
  }

}
