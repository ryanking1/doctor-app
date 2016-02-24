import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void allDoctorsAreCreatedWithName() {
  Doctor testDoctor = new Doctor("Bob");
  assertTrue(testDoctor.getName() == "Bob");
  }
}
