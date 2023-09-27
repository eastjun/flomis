package com.fromis.fromis.service;


import com.fromis.fromis.entity.Board;
import com.fromis.fromis.repository.BoardRepository;
import com.fromis.fromis.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 목록
    public List<Board> boardList(){
        return boardRepository.findAllDesc();
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
