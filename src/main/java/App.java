import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("doctors", Doctor.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/add/specialty", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-specialty.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/new/doctor", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String doctorName = request.queryParams("doctorName");
      Doctor newDoctor = new Doctor(doctorName);
      newDoctor.save();
      List<Doctor> doctorList = newDoctor.all();
      model.put("doctors", doctorList);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("delete/doctor/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      Doctor.deleteDoctor(id);
      model.put("doctors", Doctor.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/new/specialty", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Doctor doctor = Doctor.find(Integer.parseInt(request.params(":id")));
      List<Patient> patients = doctor.getPatients();
      model.put("doctor", doctor);
      model.put("patients", patients);
      model.put("template", "templates/doctor.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Doctor doctor = Doctor.find(Integer.parseInt(request.queryParams("doctorId")));
      int doctorId = Integer.parseInt(request.queryParams("doctorId"));
      String name = request.queryParams("patientName");
      Patient newPatient = new Patient(name, doctorId);
      newPatient.save();
      List<Patient> patients = doctor.getPatients();
      model.put("doctor", doctor);
      model.put("patients", patients);
      model.put("template", "templates/doctor.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
