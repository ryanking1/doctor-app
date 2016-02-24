import org.junit.*;
import static org.junit.Assert.*;

public class SpecialtyTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void allSpecialtiesAreCreatedWithASpecialty() {
  Specialty testSpecialty = new Specialty("neurologist");
  assertTrue(testSpecialty.getSpecialty() == "neurologist");
  }

}
