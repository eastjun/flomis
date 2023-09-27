package com.fromis.fromis.controller;

import com.fromis.fromis.entity.Notice;
import com.fromis.fromis.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //글 목록
    @GetMapping("/notice/list")
    public String notice(Model model){

        model.addAttribute("list",noticeService.noticeList());

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

            noticeService.write(notice);
            model.addAttribute("message","글 작성이 완료되었습니다.");
            model.addAttribute("searchUrl","/notice/list");

       return "message";
    }
    //특정 글 읽기
    @GetMapping("/notice/view")
    public String noticeSelect(Model model,Integer num){
        //System.out.println("넘어오는 값을 확인----"+ num);
        model.addAttribute("notice",noticeService.noticeView(num));
        return "/notice/noticeview";
    }
    //글 삭제
    @GetMapping("/notice/delete")
    public String noticeDelete(Integer num, Model model){
        noticeService.noticeDelete(num);
        model.addAttribute("message","삭제되었습니다.");
        model.addAttribute("searchUrl","/notice/list");
        return "message";

    }
    //글 수정
    @GetMapping("/notice/edit/{num}")
    public String noticeEdit(@PathVariable("num") Integer num, Model model){
        model.addAttribute("notice",noticeService.noticeView(num));
        return"/notice/noticeedit";
    }
    @PostMapping("/notice/edit/{num}")
    public  String noticeUpdate(@PathVariable("num")Integer num, Notice notice){
        Notice noticeTemp = noticeService.noticeView(num);
        noticeTemp.setTitle(notice.getTitle());
        noticeTemp.setContent(notice.getContent());
        noticeService.write(noticeTemp);
        return "redirect:/notice/list";
    }
}
