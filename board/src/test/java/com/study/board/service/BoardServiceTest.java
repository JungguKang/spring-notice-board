package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    void postBoardTest() {
        //given
        Board board = new Board();
        board.setTitle("spring title test");
        board.setContent("spring content testing hello world");

        //when
        Integer saveId = boardService.write(board);

        //then
        Board findPost = boardService.boardView(saveId);
        assertThat(board).isEqualTo(findPost);
    }

    
}