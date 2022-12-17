package design.boilerplate.springboot.security.service;


import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.security.dto.SessionOpenningRequest;
import design.boilerplate.springboot.security.dto.SessionReportDto;
import design.boilerplate.springboot.security.dto.SessionRequest;

public interface SessionService {
  public void createSession(SessionRequest topic);
  public Session getSession(Long sessionId);
  public void beginSession(SessionOpenningRequest topic);
}
