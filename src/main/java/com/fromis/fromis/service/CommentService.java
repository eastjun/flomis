package com.fromis.fromis.service;

import com.fromis.fromis.DTO.CommentDTO;
import com.fromis.fromis.entity.Board;
import com.fromis.fromis.entity.Comment;
import com.fromis.fromis.repository.BoardRepository;
import com.fromis.fromis.repository.CommentRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepositoy commentRepositoy;

    private final BoardRepository boardRepository;


    public Long save(CommentDTO commentDTO){

        //부모 엔티티 조회
        Optional<Board> optionalBoard = boardRepository.findById(commentDTO.getBoardId());
        if (optionalBoard.isPresent()){
            Board board = optionalBoard.get();
            Comment comment= Comment.toSaveEntity(commentDTO, board);
            return commentRepositoy.save(comment).getId();
        }
        else {
            return null;
        }
    }


    public List<CommentDTO> findAll(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        List<Comment> commentList =commentRepositoy.findAllByBoardOrderByIdDesc(board);
        /*엔티티리스트 -> DTO 리스트*/
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment: commentList){
            CommentDTO commentDTO = CommentDTO.toCommentDTO(comment, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
