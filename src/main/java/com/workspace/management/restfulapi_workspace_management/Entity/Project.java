package com.workspace.management.restfulapi_workspace_management.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int project_id;

    @Column(nullable = false, name = "company_name")
    private String company_name;


    @Column(nullable = false, name = "description")
    private String description;


    @Column(nullable = false, name = "requirements")
    private String requirements;


    @Column(nullable = false, name = "manager")

    private String manager;

    @Column(nullable = false, name = "start_date")
    private Date start_date = new Date();



    @Column( name = "end_date")
    private Date end_date = new Date();

    @Column(nullable = false,name = "opening")
    private int opening;

    @Column(name = "resources")
    private String resources;


    @JsonIgnore
    @ManyToMany(mappedBy="applied_projects")
    private Set<Student> applied_students;

    @JsonIgnore
    @ManyToMany(mappedBy="guided_projects")
    private Set<Staff> staff_incharge;

    public Set<Staff> getStaff_incharge() {
        return staff_incharge;
    }

    public void setStaff_incharge(Set<Staff> staff_incharge) {
        this.staff_incharge = staff_incharge;
    }

    public Set<Student> getApplied_students() {
        return applied_students;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy="on_going_project",cascade = CascadeType.ALL)
    private Set<Student> working_students;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy="project",cascade = CascadeType.ALL)
    private Set<Document> documents;

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @JsonIgnore
    @ManyToMany(mappedBy="funded_projects")
    private Set<Fund> funds;

    public Set<Fund> getFunds() {
        return funds;
    }

    public void setFunds(Set<Fund> funds) {
        this.funds = funds;
    }

    public void setApplied_students(Set<Student> applied_students) {
        this.applied_students = applied_students;
    }

    public Set<Student> getWorking_students() {
        return working_students;
    }

    public void setWorking_students(Set<Student> working_students) {
        this.working_students = working_students;
    }

    public Project() {
        super();
    }

    public Project(int project_id, String company_name, String description, String requirements, String manager, Date start_date, Date end_date, int opening, String resources) {
        super();
        this.project_id = project_id;
        this.company_name = company_name;
        this.description = description;
        this.requirements = requirements;
        this.manager = manager;
        this.start_date = start_date;
        this.end_date = end_date;
        this.opening = opening;
        this.resources = resources;
    }
    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getOpening() {
        return opening;
    }

    public void setOpening(int opening) {
        this.opening = opening;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", company_name='" + company_name + '\'' +
                ", description='" + description + '\'' +
                ", requirements='" + requirements + '\'' +
                ", manager='" + manager + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", opening=" + opening +
                ", resources='" + resources + '\'' +
                '}';
    }
}
