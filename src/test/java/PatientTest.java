import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void allPatientsAreCreatedWithNameAndBirthday() {
  Patient testPatient = new Patient("Jim", "07/07/2007");
  assertEquals(testPatient.getName(), "Jim");
  assertEquals(testPatient.getBirthday(), "07/07/2007");
  }
}
