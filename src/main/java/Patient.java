import java.util.List;
import org.sql2o.*;

public class Patient {
  private int id;
  private int doctorId;
  private String name;
  private String birthday;

  public Patient(String name, int doctorId) {
    this.name = name;
    this.doctorId = doctorId;
    }

  public String getName() {
    return name;
    }

  public String getBirthday() {
    return birthday;
    }

  public int getId() {
    return id;
  }

  public static List<Patient> all() {
  String sql = "SELECT doctorId, name FROM patients";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Patient.class);
  }
}

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients(doctorId, name) VALUES (:doctorId, :name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("doctorId", this.doctorId)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }
}
