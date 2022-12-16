package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SessionRepository extends JpaRepository<Session, Long> {

}
