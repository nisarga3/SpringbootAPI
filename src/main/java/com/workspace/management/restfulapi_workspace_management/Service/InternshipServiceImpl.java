package com.workspace.management.restfulapi_workspace_management.Service;


import com.workspace.management.restfulapi_workspace_management.Dao.InternshipDao;

import com.workspace.management.restfulapi_workspace_management.Dao.StudentDao;
import com.workspace.management.restfulapi_workspace_management.Entity.Internship;
import com.workspace.management.restfulapi_workspace_management.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InternshipServiceImpl implements InternshipService {


    @Autowired
    private  InternshipDao internshipDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Internship> getInternships() {
        return null;
    }

    @Override
    public List<Student> getInternshipAppliedStudent(int internship_id) {
        return null;
    }

    @Override
    public void addInternship(Internship internship) {
    }

}