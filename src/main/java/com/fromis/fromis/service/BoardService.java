package com.fromis.fromis.service;


import com.fromis.fromis.entity.Board;
import com.fromis.fromis.repository.BoardRepository;
import com.fromis.fromis.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 목록
    public Page<Board> boardList(Pageable pageable){

        return boardRepository.findAll(pageable);
    }
    //글 작성
    public void boardwrite(Board board){

        if (board.getHit()==null){
        board.setHit(0);
        }
        boardRepository.save(board);
    }
    //글 보기
    public Board boardView(Integer num){
        Board board = boardRepository.findById(num).get();
        if (board !=null){
            board.setHit(board.getHit()+1);
            boardRepository.save(board);
        }
        return board;
    }
    //글 삭제
    public void boardDelete(Integer num){

        boardRepository.deleteById(num);
    }
    
    
}
