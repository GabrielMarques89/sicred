package util;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.Topic;
import java.time.LocalDateTime;

public class ModelHelper {

  public static final String DEFAULT_TOPIC_NAME = "Pauta 1";
  public static final long DEFAULT_TOPIC_ID = 1L;
  public static final long DEFAULT_SESSION_ID = 33L;
  public static final LocalDateTime NOW = LocalDateTime.now();

  public static Session mockSession() {
    var model = new Session();

    model.setId(DEFAULT_SESSION_ID);
    model.setTopic(mockTopic());
    model.setBeginDateTime(NOW);

    return model;
  }

  private static Topic mockTopic() {
    var topic = new Topic();
    topic.setId(DEFAULT_TOPIC_ID);
    topic.setName(DEFAULT_TOPIC_NAME);
    return topic;
  }

}
