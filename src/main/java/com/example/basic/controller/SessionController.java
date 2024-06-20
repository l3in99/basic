package com.example.basic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.entity.Board;
import com.example.basic.model.BoardModel;
import com.example.basic.model.User;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
    @Autowired UserRepository ur;
    @Autowired BoardRepository br;

    // get 형식으로 최초의 페이지 출력
    @GetMapping("/board")
    public String board() {
        return "board";
    }

    // post 형식으로 페이지의 내용 전달
    @PostMapping("/board")
                         // 보드모델 형식으로 값을 입력받을 것을 의미
    public String boardPost(BoardModel boardModel) {
        // 보드 메소드 호출
        Board board = new Board();
        // 데이터베이스 내에 보드의 title을 입력받은 보드의 내용으로 설정
        board.setTitle(boardModel.getTitle());
        // 설정된 값을 board(entity, 데이터베이스)에 저장
        br.save(board);
        return "redirect:/main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> loginPost(@RequestBody User user, HttpSession session) {

        Map<String, Object> map = new HashMap<>();

        // 모델 User와 entity의 User의 이름이 똑같아서 수동으로 임포트
        com.example.basic.entity.User dbuser = ur.findByUserIdAndUserPw(user.getUserId(), user.getUserPw());
        // 받아온 값이 데이터베이스 내에 존재하지 않을 경우
        if(dbuser == null) {
            // 사용자를 다시 로그인 화면으로 퇴출
            // 가능하다면 메세지를 출력하는 방식으로
            map.put("msg", "ID 또는 PW를 확인해주세요.");
            return map;
        }
        // 받아온 값이 데이터베이스 내의 내용과 일치할 경우 user값을 세션에 반환
        session.setAttribute("user", user);
        map.put("msg", "로그인되었습니다.");
        return map;
    }

    @GetMapping("/main")
    public String main(HttpSession session) {
        User u = (User) session.getAttribute("user");
        if(u == null) {
            return "redirect:/login";
        }
        String id = u.getUserId();
        String pw = u.getUserPw();
        System.out.println(id + " " + pw);
        return "main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        session.removeAttribute("user");
        return "redirect:/login";
    }
    
}
