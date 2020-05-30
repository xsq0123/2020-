package com.example.de.dao;

import com.example.de.domain.Project;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query("select u from Project u where pname like ?1")
    public Page<Project> findByKeyword(String kw, Pageable pageable);

    @Query("select u from Project u where buyer_id = ?1")
    public Page<Project> findMyProjectByKeyword(int kw, Pageable pageable);

//    @Query(name = "findItemByBuyer_id",nativeQuery = true,value =
//            "select * from project_info where buyer_id = :id")
//    List<Project> findItemByBuyer_id(@Param("id")Integer id);

   // @Select("select * from project_info")
   // public List<Project> selectProjectInfo();

//    @Select("select * from project_info where pid=#{pid}")
//   public Project selectProjectInfoById(@Param("pid") int pid);
}
