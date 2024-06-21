package com.example.basic.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.entity.BoardFileTest;
import com.example.basic.entity.BoardTest;
import com.example.basic.repository.BoardFileTestRepository;
import com.example.basic.repository.BoardTestRepository;


@RestController
public class BoardTestController {
    @Autowired BoardTestRepository br;
    @Autowired BoardFileTestRepository bftr;

    // 관례적으로 작성하는 롤백포
    @Transactional(rollbackFor = {Exception.class, IOException.class})
    @GetMapping("/board_test")
    public String boardTest(@ModelAttribute BoardTest board) {
        BoardTest dbBoard = br.save(board);

        BoardFileTest file = new BoardFileTest();
        file.setFileName("A");
        file.setBoard(dbBoard);

        if(true) { // runtimeException에서만 롤백 가능
        throw new RuntimeException();
        }

        bftr.save(file);

        return "완료";
    }
}
