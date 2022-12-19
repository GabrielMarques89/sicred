package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.Vote;
import design.boilerplate.springboot.model.VoteResult;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoteRepository extends JpaRepository<Vote, Long> {

  boolean existsByUserAndTopic(User user, Topic topic);

  Long countByTopic(Topic topic);

  Long countBySession(Session topic);

  Long countByTopicAndVoteResult(Topic topic, VoteResult voteResult);
}
