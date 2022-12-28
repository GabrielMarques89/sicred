package design.boilerplate.springboot.service.bootloader;

import design.boilerplate.springboot.service.interfaces.UserService;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PostContructImpl {

	private final UserService userService;

	public PostContructImpl(UserService service) {
		userService = service;
	}

	@PostConstruct
	public void runAfterObjectCreated() {
		try{
			userService.createAdmin();
		}catch(Exception e){
			log.error("Error creating admin user", e);
		}
	}

}