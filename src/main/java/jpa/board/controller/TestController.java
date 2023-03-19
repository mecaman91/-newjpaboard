package jpa.board.controller;

import jpa.board.dto.BoardDto;
import jpa.board.entity.Board;
import jpa.board.entity.Member;
import jpa.board.repository.BoardRepository;
import jpa.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;



@Controller
@RequiredArgsConstructor
public class TestController {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @GetMapping("/test")
    public String main(){
        return "views/content.html";
    }

    @GetMapping("/dataInsert")
    public void dataInsert(){
        for(int i=0; i<100; i++){
            //given
            String title = "타이틀"+i;
            String content = "내용"+i;

            List<Member> memberList = memberRepository.findAll();
            Member member = memberList.get(0);

            BoardDto boardDto = BoardDto.builder()
                    .title(title)
                    .content(content)
                    .build();

            Board board = boardDto.toEntity(member);
            boardRepository.save(board);
        }
    }
}