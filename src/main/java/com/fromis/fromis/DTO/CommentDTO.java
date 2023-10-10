package com.fromis.fromis.DTO;

import com.fromis.fromis.entity.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {

    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;


    public static CommentDTO toCommentDTO(Comment comment, Long boardId) {
        CommentDTO commentDTO =new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setCommentWriter(comment.getCommentWriter());
        commentDTO.setCommentContents(comment.getCommentContents());

        commentDTO.setCommentCreatedTime(comment.getCreatedTime());
        commentDTO.setBoardId(boardId);
        return commentDTO;
    }
}
