package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
