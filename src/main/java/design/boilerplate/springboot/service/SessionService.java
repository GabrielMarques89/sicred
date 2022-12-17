package design.boilerplate.springboot.service;


import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.dto.SessionOpenningRequest;
import design.boilerplate.springboot.model.dto.SessionRequest;

public interface SessionService {
  public void createSession(SessionRequest topic);
  public Session getSession(Long sessionId);
  public void beginSession(SessionOpenningRequest topic);
}
