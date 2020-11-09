package com.cokreates.grp.beans.pim.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,String> {

    List<Grade> findByNameBnAndIsDeleted(String nameBn, String isDeleted);

}
