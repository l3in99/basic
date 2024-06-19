package com.example.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.basic.entity.Board;
import com.example.basic.model.BoardModel;
import com.example.basic.model.User;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class SessionController {
    @Autowired UserRepository ur;
    @Autowired BoardRepository br;

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @PostMapping("/board")
    public String boardPost(BoardModel boardModel) {
        Board board = new Board();
        board.setTitle(boardModel.getTitle());
        br.save(board);
        return "redirect:/main";
    }
    
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(User user, HttpSession session) {
        com.example.basic.entity.User dbuser = ur.findByUserIdAndUserPw(user.getUserId(), user.getUserPw());
        if(dbuser == null) {
            return "redirect:/login";
        }
        session.setAttribute("user", user);
        return "redirect:/main";
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
