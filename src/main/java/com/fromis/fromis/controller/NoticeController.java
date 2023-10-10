package com.fromis.fromis.controller;

import com.fromis.fromis.entity.Notice;
import com.fromis.fromis.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //로그인한 정보에서 닉네임 가져오기
    private String getCurrentUserNickname() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername(); // 여기에서 닉네임이 사용자의 username으로 가정합니다.
        } else {
            return null; // 사용자 정보를 가져올 수 없는 경우
        }
    }
    //글 목록
    @GetMapping("/notice/list")
    public String notice(Model model, @PageableDefault(page = 0,size = 10,sort = "num",direction = Sort.Direction.DESC) Pageable pageable){


        Page<Notice> list = noticeService.noticeList(pageable);

        int nowPage= list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4,1);
        int endPage= Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "/notice/notice";
    }
    //글 작성 페이지 이동
    @GetMapping("/notice/write")
    public String noticeWrite(){


        return "/notice/noticewrite";
    }
    //글 작성
    @PostMapping("/notice/write")
    public String noticeInsert(Notice notice, Model model){

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = authentication.getName();
            notice.setUsername(currentUsername);

            noticeService.write(notice);
            model.addAttribute("message","글 작성이 완료되었습니다.");
            model.addAttribute("searchUrl","/notice/list");

       return "message";
    }
    //특정 글 읽기
    @GetMapping("/notice/view")
    public String noticeSelect(Model model,Long num){
        //System.out.println("넘어오는 값을 확인----"+ num);
        model.addAttribute("notice",noticeService.noticeView(num));
        return "/notice/noticeview";
    }
    //글 삭제
    @GetMapping("/notice/delete")
    public String noticeDelete(Long num, Model model){
        noticeService.noticeDelete(num);
        model.addAttribute("message","삭제되었습니다.");
        model.addAttribute("searchUrl","/notice/list");
        return "message";

    }
    //글 수정
    @GetMapping("/notice/edit/{num}")
    public String noticeEdit(@PathVariable("num") Long num, Model model){
        model.addAttribute("notice",noticeService.noticeView(num));
        return"/notice/noticeedit";
    }
    @PostMapping("/notice/edit/{num}")
    public  String noticeUpdate(@PathVariable("num")Long num, Notice notice){
        Notice noticeTemp = noticeService.noticeView(num);
        noticeTemp.setTitle(notice.getTitle());
        noticeTemp.setContent(notice.getContent());
        noticeService.write(noticeTemp);
        return "redirect:/notice/list";
    }
}
