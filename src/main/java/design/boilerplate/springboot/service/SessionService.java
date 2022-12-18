package design.boilerplate.springboot.service;


import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.dto.SessionCreationResponse;
import design.boilerplate.springboot.model.dto.SessionOpenningRequest;
import design.boilerplate.springboot.model.dto.SessionOpenningResponse;
import design.boilerplate.springboot.model.dto.SessionRequest;

public interface SessionService {
  Session createSession(SessionRequest topic);
  SessionCreationResponse createSessionV2(SessionRequest topic);
  Session getSession(Long sessionId);
  SessionOpenningResponse beginSession(SessionOpenningRequest topic);
}
