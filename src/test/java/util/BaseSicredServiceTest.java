package util;

import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BaseSicredServiceTest {

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

}
