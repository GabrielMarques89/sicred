package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoteRepository extends JpaRepository<Vote, Long> {

  boolean existsByUserAndTopic(User user, Topic topic);

}
