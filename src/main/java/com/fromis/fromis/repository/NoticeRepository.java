package com.fromis.fromis.repository;

import com.fromis.fromis.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

//    @Query("SELECT n FROM Notice n ORDER BY n.num DESC")
//    List<Notice> findAllDesc();

}
