package design.boilerplate.springboot.security.service;


import design.boilerplate.springboot.security.dto.SessionRequest;
import design.boilerplate.springboot.security.dto.TopicRequest;

public interface SessionService {
  public void createSession(SessionRequest topic);
}
