package com.workspace.management.restfulapi_workspace_management.Controller;

import com.workspace.management.restfulapi_workspace_management.Dao.FundDao;
import com.workspace.management.restfulapi_workspace_management.Entity.*;
import com.workspace.management.restfulapi_workspace_management.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
public class AdminController {

    @Autowired
   private  ProjectService projectService;
    @Autowired
    private InternshipService internshipService;
    @Autowired
    private EventService eventService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FundService fundService;

    @Autowired
    private StaffService staffService;



    @GetMapping(path = "/project-applied-students/{project_id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getProjectAppliedStudent(@PathVariable String project_id) {
        return new ResponseEntity<>(this.projectService.getProjectAppliedStudent(Integer.parseInt(project_id)), HttpStatus.OK);
    }

    @GetMapping(path = "/internship-applied-students/{internship_id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getInternshipAppliedStudent(@PathVariable String internship_id) {
        return new ResponseEntity<>(this.internshipService.getInternshipAppliedStudent(Integer.parseInt(internship_id)), HttpStatus.OK);
    }

    @GetMapping(path = "/event-applied-students/{event_id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getEventAppliedStudent(@PathVariable String event_id) {
        return new ResponseEntity<>(this.eventService.getEventAppliedStudent(Integer.parseInt(event_id)), HttpStatus.OK);
    }

    @PostMapping(path = "/add-project", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addProject(@RequestBody Project project) {
            return new ResponseEntity<>(this.projectService.addProject(project));
    }

    @PostMapping(path = "/add-internship", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addInternship(@RequestBody Internship internship) {
            return new ResponseEntity<>( this.internshipService.addInternship(internship));
    }
    @PostMapping(path = "/add-event", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addEvent(@RequestBody Event event) {
            return new ResponseEntity<>(this.eventService.addEvent(event));
    }
    @PostMapping(path = "/hire-project",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> hireProject(@RequestBody Map<String,String> json) {
        return new ResponseEntity<>(projectService.hireProject(json.get("USN"), Integer.parseInt(json.get("project_id"))));
    }
    @PostMapping(path = "/hire-internship",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> hireInternship(@RequestBody Map<String,String> json) {
        return new ResponseEntity<>(internshipService.hireInternship(json.get("USN"), Integer.parseInt(json.get("internship_id"))));
    }

    @PostMapping(path = "/upload-project-document/{project_id}")
    public ResponseEntity<List<Object>> uploadProjectDocumentToDB(@RequestParam("file") MultipartFile[] files,@PathVariable String project_id) {
        return projectService.uploadProjectDocument(files,Integer.parseInt(project_id));
    }
    @PostMapping(path = "/upload-project-document/{internship_id}")
    public ResponseEntity<List<Object>> uploadInternshipDocumentToDB(@RequestParam("file") MultipartFile[] files,@PathVariable String internship_id) {
        return internshipService.uploadInternshipDocument(files,Integer.parseInt(internship_id));
    }
    @PostMapping(path = "/upload-project-document/{event_id}")
    public ResponseEntity<List<Object>> uploadEventDocumentToDB(@RequestParam("file") MultipartFile[] files,@PathVariable String event_id) {
        return eventService.uploadEventDocument(files,Integer.parseInt(event_id));
    }

    @PostMapping(path = "/student",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> uploadEventDocumentToDB(@RequestBody Map<String,String> json) {
        return studentService.studentDetails(json.get("USN"));
    }

    @GetMapping(path = "/funds",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Fund>> getFunds()
    {
        return  this.fundService.getFunds();
    }

    @PostMapping(path = "/add-fund", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addFund(@RequestBody Fund fund) {
        return new ResponseEntity<>(this.fundService.addFund(fund));
    }
    @GetMapping(path = "/students",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getRegisteredStudents() {
        return new ResponseEntity<>(studentService.getRegisteredStudent(),HttpStatus.OK);
    }

    @GetMapping(path = "/all-project-applied-students",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> allProjectApplied() {
        return new ResponseEntity<>(projectService.allAppliedStudent(),HttpStatus.OK);
    }


    @GetMapping(path = "/all-internship-applied-students",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> allInternshipApplied() {
        return new ResponseEntity<>(internshipService.allAppliedInternsipStudent(),HttpStatus.OK);
    }

    @GetMapping(path = "/all-event-applied-students",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> allEventApplied() {
        return new ResponseEntity<>(eventService.allAppliedEventStudent(),HttpStatus.OK);
    }
    @PostMapping(path = "/register-staff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object registerStaff(@RequestBody Staff staff) {
        return  staffService.addStaff(staff);
    }

    @GetMapping(path = "/get-staff",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Staff> getStaffList() {
        return staffService.getAllStaff();
    }

    @GetMapping(path = "/studentsCSV",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentCSV>> getCSVContent() {
        List<StudentCSV> result = new ArrayList<>();
        List<Student> students = studentService.getRegisteredStudent();
        for(Student s : students)
        {
          StudentCSV scv = new StudentCSV(s.getStudent_first_name(),s.getStudent_mid_name(),s.getStudent_last_name(),s.getBatch(),s.getUSN(),s.getDepartment(),s.getPhone_number(),s.getEmail_id());
            Set<Project> p = s.getApplied_projects();
            Set<Internship> i = s.getApplied_internships();
            Set<Event> e = s.getApplied_events();
            String applied_p = "";
            for(Project pp : p ){
              applied_p.concat("," + pp.getCompany_name());
            }
            scv.setApplied_project_company_name(applied_p);
            String applied_i = "";
            for(Internship ii : i ){
                applied_i.concat("," + ii.getCompany_name());
            }
            scv.setApplied_project_company_name(applied_i);
            Project onp = s.getOn_going_project();
            if(onp!=null)
            scv.setOn_going_project_company_name(onp.getCompany_name());
            else scv.setOn_going_project_company_name("null");

            Internship oni = s.getOn_going_internship();
            if(oni!=null)
            scv.setOn_going_internship_company_name(oni.getCompany_name());
            else  scv.setOn_going_internship_company_name("null");

            result.add(scv);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
