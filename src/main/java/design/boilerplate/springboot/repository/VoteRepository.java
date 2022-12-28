package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.entities.Session;
import design.boilerplate.springboot.model.entities.Topic;
import design.boilerplate.springboot.model.entities.User;
import design.boilerplate.springboot.model.entities.Vote;
import design.boilerplate.springboot.model.enums.VoteResult;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoteRepository extends JpaRepository<Vote, Long> {

  boolean existsByUserAndSession(User user, Session session);

  Long countBySession(Session topic);

  Long countBySessionAndVoteResult(Session session, VoteResult voteResult);
}
