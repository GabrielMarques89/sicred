package util;

import design.boilerplate.springboot.model.dto.SessionOpenningRequest;
import design.boilerplate.springboot.model.dto.SessionRequest;
import design.boilerplate.springboot.model.dto.TopicRequest;
import java.time.LocalDateTime;

public class DtoHelper {

  public static final long DEFAULT_TOPIC_ID = 1L;
  public static final LocalDateTime NOW = LocalDateTime.now();
  public static final long DEFAULT_SESSION_ID = 33L;
  public static final int DEFAULT_DURATION = 30;

  public static SessionRequest mockSessionRequest() {
    var req = new SessionRequest();

    req.setTopic(DEFAULT_TOPIC_ID);
    req.setBeginDateTime(NOW);

    return req;
  }

  public static TopicRequest mockTopicRequest() {
    var req = new TopicRequest();

    req.setName("Topic 1");
    return req;
  }

  public static SessionOpenningRequest mockSessionOpenningRequest() {
    var req = new SessionOpenningRequest();

    req.setSession(DEFAULT_SESSION_ID);
    req.setDuration(DEFAULT_DURATION);

    return req;
  }

}
