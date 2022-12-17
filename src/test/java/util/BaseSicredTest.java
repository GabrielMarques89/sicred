package util;

import static org.mockito.Mockito.clearInvocations;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class BaseSicredTest extends AbstractTestNGSpringContextTests {

  @BeforeClass()
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }
}
