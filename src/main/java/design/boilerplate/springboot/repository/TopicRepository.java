package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicRepository extends JpaRepository<Topic, Long> {

  boolean existsByName(String name);
}
