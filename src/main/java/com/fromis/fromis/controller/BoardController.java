package com.fromis.fromis.controller;

import com.fromis.fromis.DTO.CommentDTO;
import com.fromis.fromis.entity.Board;
import com.fromis.fromis.service.BoardService;
import com.fromis.fromis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;
    
    //글 목록
    @GetMapping("/board")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Board> list= boardService.boardList(pageable);
        int nowPage= list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4,1);
        int endPage= Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "board/board";
    }
    //글 작성 페이지 이동
    @GetMapping("/board/write")
    public String boardWrite(){

        return "board/boardwrite";
    }
    //글 작성
    @PostMapping("/board/write")
    public String boardInsert(Board board, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        board.setUsername(currentUsername);

        boardService.boardwrite(board);
        model.addAttribute("message","글 작성이 완료 되었습니다.");
        model.addAttribute("searchUrl","/board");

        return "message";
    }
    //게시글 읽기
    @GetMapping("/board/view")
    public String boardSelect(Model model, Long id){
        
        //댓글 목록
        List<CommentDTO> commentDTOList= commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);
        
        model.addAttribute("board",boardService.boardView(id));
        return "board/boardview";
    }
    //글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(Long id, Model model){
        boardService.boardDelete(id);
        model.addAttribute("message","삭제되었습니다");
        model.addAttribute("searchUrl","/board");
        return "message";
    }
    //글 수정
    @GetMapping("/board/edit/{id}")
    public String boardEdit(@PathVariable("id") Long id, Model model){
        model.addAttribute("board",boardService.boardView(id));
        return "board/boardedit";
    }
    @PostMapping("/board/edit/{id}")
    public String boardUpdate(@PathVariable("id") Long id, Board board){
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardService.boardwrite(boardTemp);
        return "redirect:/board";
    }
}
