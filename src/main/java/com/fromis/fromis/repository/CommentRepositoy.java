package com.fromis.fromis.repository;

import com.fromis.fromis.entity.Board;
import com.fromis.fromis.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositoy extends JpaRepository<Comment,Long> {

    // select * from comment_table where board_id=? order by id desc;
    List<Comment> findAllByBoardOrderByIdDesc(Board board);
}
