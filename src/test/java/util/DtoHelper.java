package util;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.dto.SessionOpenningRequest;
import design.boilerplate.springboot.model.dto.SessionRequest;
import java.time.LocalDateTime;

public class DtoHelper {

  public static final long DEFAULT_TOPIC_ID = 1L;
  public static final LocalDateTime NOW = LocalDateTime.now();
  public static final long DEFAULT_SESSION_ID = 33L;
  public static final int DEFAULT_DURATION = 30;

  public static SessionRequest mockRequest() {
    var req = new SessionRequest();

    req.setTopic(DEFAULT_TOPIC_ID);
    req.setBeginDateTime(NOW);

    return req;
  }

  public static SessionOpenningRequest mockSessionOpenningRequest() {
    var req = new SessionOpenningRequest();

    req.setSession(DEFAULT_SESSION_ID);
    req.setDuration(DEFAULT_DURATION);

    return req;
  }

}
