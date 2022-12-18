package design.boilerplate.springboot.service.performance;

import static io.gatling.javaapi.core.CoreDsl.AllowList;
import static io.gatling.javaapi.core.CoreDsl.DenyList;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.arrayFeeder;
import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.repeat;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static util.DtoHelper.generateUserRegistrationRequest;

import com.google.gson.Gson;
import design.boilerplate.springboot.model.VoteResult;
import design.boilerplate.springboot.model.dto.LoginRequest;
import design.boilerplate.springboot.model.dto.SessionDto;
import design.boilerplate.springboot.model.dto.SessionOpenningDto;
import design.boilerplate.springboot.model.dto.TopicRequest;
import design.boilerplate.springboot.model.dto.UserRegistrationRequest;
import design.boilerplate.springboot.model.dto.VoteRequest;
import design.boilerplate.springboot.model.dto.VoteRequestDto;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomStringUtils;

public class PerformanceSimulation extends Simulation {

  public static final String HTTP_LOCALHOST_8080 = "http://localhost:8080";
  private static final Gson gson = new Gson();
  public static final String GZIP_DEFLATE = "gzip, deflate";
  public static final String ACCEPT_HEADER = "*/*";
  public static final String CONTENT_TYPE = "application/json";
  public static final String AGENT_HEADER = "PostmanRuntime/7.30.0";
  public static final String USER_CREATION_URL = "/user";
  public static final int HTTTP_CREATED = 201;
  public static final int HTTP_OK = 200;
  private static final Random random = new Random();
  private Long sessionId;

  public PerformanceSimulation() {
    var prepareSessionScenario = prepareSession().injectOpen(rampUsers(3).during(15));
    setUp(prepareSessionScenario).protocols(httpProtocol());
  }

  private HttpProtocolBuilder httpProtocol() {
    return http.baseUrl(HTTP_LOCALHOST_8080)
        .inferHtmlResources(AllowList(), DenyList())
        .acceptHeader(ACCEPT_HEADER)
        .acceptEncodingHeader(GZIP_DEFLATE)
        .contentTypeHeader(CONTENT_TYPE)
        .userAgentHeader(AGENT_HEADER);
  }

  private ChainBuilder createTopic(TopicRequest topic) {
    return exec(http("create_topic").post("/topic/v2").header("Authorization", "Bearer #{token}")
        .body(StringBody(gson.toJson(topic)))
        .check(status().is(HTTTP_CREATED))
        .check(jsonPath("$.id").saveAs("topicId"))
    );
  }

  private ChainBuilder loginUser(LoginRequest login) {
    return exec(http("login").post("/login")
        .body(StringBody(gson.toJson(login)))
        .check(status().is(HTTP_OK))
        .check(jsonPath("$.token").saveAs("token")));
  }

  private ChainBuilder createSession(String begin) {
    return exec(
        http("create_session").post("/session/v2").header("Authorization", "Bearer #{token}")
            .body(StringBody(gson.toJson(new SessionDto("${topicId}", begin)))).asJson()
            .check(status().is(HTTTP_CREATED))
            .check(jsonPath("$.id").saveAs("sessionId")));
  }
  private ChainBuilder openSession(int duration) {
    return exec(http("open_session").put("/session/begin").header("Authorization", "Bearer #{token}")
        .body(StringBody(gson.toJson(new SessionOpenningDto("${sessionId}", 10))))
        .check(status().is(HTTP_OK)));
  }

  private ChainBuilder createUser(UserRegistrationRequest user) {
    var chain = exec(http("user_creation").post(USER_CREATION_URL)
        .body(StringBody(gson.toJson(user)))
        .check(status().is(HTTTP_CREATED)))
        .pause(5);
    return chain;
  }

  private Iterator<Map<String, UserRegistrationRequest>> createFeeder() {
    return Stream.generate((Supplier<Map<String, UserRegistrationRequest>>) () -> {
          var generateUser = generateUserRegistrationRequest();
          return Collections.singletonMap("user", generateUser);
        }
    ).iterator();
  }

  private ScenarioBuilder prepareSession() {

    var user = generateUserRegistrationRequest();
    var login = new LoginRequest(user.getUsername(), user.getPassword());
    var topic = new TopicRequest("Topic of user " + user.getUsername());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    var begin = LocalDateTime.now().minusMinutes(5).format(formatter);

    var vote = new VoteRequest();

    return scenario("creating_session").exec(createUser(user), loginUser(login), createTopic(topic),
        createSession(begin), openSession(15));
  }

  private VoteResult randomVote() {
    return random.nextInt(2) == 0 ? VoteResult.YES : VoteResult.NO;
  }

  private ScenarioBuilder createUserAndVote() {
    var user = generateUserRegistrationRequest();
    var login = new LoginRequest(user.getUsername(), user.getPassword());
    var vote = new VoteRequest(18L, randomVote());

    return scenario("userCreationAndVote")
        .exec(
            http("user_creation")
                .post(USER_CREATION_URL)
                .body(StringBody(gson.toJson(user)))
                .check(status().is(HTTTP_CREATED)))
        .exec(
            http("login")
                .post("/login")
                .body(StringBody(gson.toJson(login)))
                .check(status().is(HTTP_OK))
                .check(jsonPath("$.token").saveAs("token")))
        .exec(
            http("vote")
                .post("/vote")
                .body(StringBody(gson.toJson(new VoteRequestDto("#{sessionId}", randomVote()))))
                .check(status().is(HTTP_OK))
        );
  }
}
