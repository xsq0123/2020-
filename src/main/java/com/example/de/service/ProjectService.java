package com.example.de.service;

import com.example.de.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    public Page<Project> findAll(String kw, Pageable pageable);
    public Page<Project> findAllMyProject(int kw, Pageable pageable);
    public  void deletes(List<Project> users);
    public  void deleteById(Integer uid);
    public Project fingById(Integer pid);
    public void save(Project p) ;

    //public List<Project> getProjectInfo();
    //public Project getProjectInfoById(int oid);
}
