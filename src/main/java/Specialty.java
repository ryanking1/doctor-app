import java.util.List;
import org.sql2o.*;

public class Specialty {
  private int id;
  private String specialty;

  public Specialty(String specialty) {
    this.specialty = specialty;
    }

  public String getSpecialty() {
    return specialty;
  }

  public int getId() {
    return id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO specialties(specialty) VALUES (:specialty)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("specialty", this.specialty)
        .executeUpdate()
        .getKey();
    }
}
  //   public static List<Specialty> all() {
  //   String sql = "SELECT id, name, birthday FROM patients";
  //   try(Connection con = DB.sql2o.open()) {
  //     return con.createQuery(sql).executeAndFetch(Specialty.class);
  //   }
  // }
}
