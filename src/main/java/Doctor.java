import java.util.List;
import org.sql2o.*;

public class Doctor {
private int id;
private String name;

public Doctor(String name) {
  this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Doctor> all() {
  String sql = "SELECT * FROM doctors";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Doctor.class);
  }
}

public static void deleteDoctor(int id) {
      String sql = "DELETE FROM doctors WHERE id=:id";
      try(Connection con = DB.sql2o.open()) {
        con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO doctors (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public List<Patient> getPatients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients where doctorId=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Patient.class);
    }
  }

  public static Doctor find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM doctors where id=:id";
    Doctor Doctor = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Doctor.class);
    return Doctor;
  }
  }
}
