package com.tasks.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.tasks.management.models.Status;

public interface StatusRepos extends JpaRepository<Status, Integer> {

    @Query("SELECT u FROM Status u WHERE u.id = :id ")
    public Optional<Status> findByDataId(@Param("id") Integer id);
    @Query("SELECT u FROM Status u WHERE u.statusName = :statusName AND u.statusType =:type AND u.statusAvailable is true GROUP BY u.statusName")
    public Optional<Status> findByTypeStatus(@Param("statusName") String statusName,@Param("type") String type);

    @Query("SELECT s FROM Status s WHERE " +
    "(:name IS NULL OR s.statusName LIKE %:name%) AND" +
    "(:type IS NULL OR s.statusType =:type)")
    public Page<Status> findByDataList(@Param("name") String name,@Param("type") String type,
    Pageable pageable);

}
