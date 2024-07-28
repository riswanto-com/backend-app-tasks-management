package com.tasks.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tasks.management.models.Tasks;
public interface TasksRepos extends JpaRepository<Tasks,Long> {
    @Query("SELECT new com.tasks.management.dto.project.ProjectSelect(p.id,p.projectName,p.projectCreateId,p.projectCreateDate,"+
    "p.projectDescription,p.projectEndDate,s.statusName,u.username,u.email) "+
    "FROM Project p JOIN p.users u JOIN p.status s WHERE " +
            "(:name IS NULL OR p.projectName LIKE %:name%)"+
            "AND u.id =:userId ORDER BY p.projectCreateDate DESC")
    public Page<Tasks> findByListPage(@Param("name") String name,@Param("userId") Integer userId,Pageable pageable);

}
