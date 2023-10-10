package com.fromis.fromis.repository;

import com.fromis.fromis.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
//    @Query("SELECT n from Board n ORDER BY n.num DESC")
//    List<Board> findAllDesc();
}
