package com.example.de.service;

import com.example.de.dao.ProjectRepository;
import com.example.de.domain.Order;
import com.example.de.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Override

    public Page<Project> findAll(String kw, Pageable pageable) {
        return projectRepository.findByKeyword(kw,pageable);
    }

    @Override
    public Page<Project> findAllMyProject(int kw, Pageable pageable) {
        return projectRepository.findMyProjectByKeyword(kw,pageable);
    }

    @Override
    @Transactional
    public void deletes(List<Project> projects) {
        double sum=0;
        for(Project u:projects){
            projectRepository.delete(u);
            sum=sum+u.getPprice();
        }
        Order order=new Order();
        order.setOprice(sum);
        System.out.println("-----------------------------------"+sum);
    }

    @Override
    public void deleteById(Integer uid) {
        projectRepository.deleteById(uid);
    }

    @Override
    public Project fingById(Integer pid) {
        return projectRepository.findById(pid).get();
    }

    @Override
    public void save(Project p) {
        projectRepository.save(p);
    }

//    @Override
//    public List<Project> getProjectInfo() {
//        return projectRepository.selectProjectInfo();
//    }

//    @Override
//    public Project getProjectInfoById(int oid) {
//        return projectRepository.selectProjectInfoById(oid);
//    }

}
