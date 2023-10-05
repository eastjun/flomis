package com.fromis.fromis.controller;

import com.fromis.fromis.entity.Board;
import com.fromis.fromis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //글 목록
    @GetMapping("/board")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "num", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Board> list= boardService.boardList(pageable);
        int nowPage= list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4,1);
        int endPage= Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "/board/board";
    }
    //글 작성 페이지 이동
    @GetMapping("/board/write")
    public String boardWrite(){

        return "/board/boardwrite";
    }
    //글 작성
    @PostMapping("/board/write")
    public String boardInsert(Board board, Model model){
        boardService.boardwrite(board);
        model.addAttribute("message","글 작성이 완료 되었습니다.");
        model.addAttribute("searchUrl","/board");

        return "message";
    }
    //게시글 읽기
    @GetMapping("/board/view")
    public String boardSelect(Model model, Integer num){
        model.addAttribute("board",boardService.boardView(num));
        return "/board/boardview";
    }
    //글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(Integer num, Model model){
        boardService.boardDelete(num);
        model.addAttribute("message","삭제되었습니다");
        model.addAttribute("searchUrl","/board");
        return "message";
    }
    //글 수정
    @GetMapping("/board/edit/{num}")
    public String boardEdit(@PathVariable("num") Integer num, Model model){
        model.addAttribute("board",boardService.boardView(num));
        return "/board/boardedit";
    }
    @PostMapping("/board/edit/{num}")
    public String boardUpdate(@PathVariable("num") Integer num, Board board){
        Board boardTemp = boardService.boardView(num);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardService.boardwrite(boardTemp);
        return "redirect:/board";
    }
}
