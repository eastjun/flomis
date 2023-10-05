package com.fromis.fromis.service;

import com.fromis.fromis.entity.Notice;
import com.fromis.fromis.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    //게시글 목록 처리
    public Page<Notice> noticeList(Pageable pageable){

        return noticeRepository.findAll(pageable);
    };
    //공지 작성
    public void write(Notice notice){

        if (notice.getHit()==null){
        notice.setHit(0);
        }
        noticeRepository.save(notice);
    };

    //특정 게시물 보기
    public Notice noticeView(Integer num){

        //System.out.println("넘어오는 지, 값 확인 ---"+ num);

        Notice notice = noticeRepository.findById(num).get();
        if (notice !=null){
            notice.setHit(notice.getHit()+1);
            noticeRepository.save(notice);
        }
        return notice;

    }
    //공지 삭제
    public void  noticeDelete(Integer num){

        noticeRepository.deleteById(num);
    }
}
