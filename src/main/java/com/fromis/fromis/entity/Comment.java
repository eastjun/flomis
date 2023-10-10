package com.fromis.fromis.entity;

import com.fromis.fromis.DTO.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentWriter;

    private String commentContents;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public static Comment toSaveEntity(CommentDTO commentDTO, Board board) {
        Comment comment = new Comment();
        comment.setCommentWriter(commentDTO.getCommentWriter());
        comment.setCommentContents(commentDTO.getCommentContents());
        comment.setBoard(board);

        return comment;
    }
}
