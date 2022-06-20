package com.study.board.service;

import com.study.board.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    private Board getTestBoard() {
        Board board = new Board();
        board.setTitle("spring title test");
        board.setContent("spring content testing hello world");
        return board;
    }
    @Test
    void postBoardTest() {
        //given
        Board board = getTestBoard();

        //when
        Integer saveId = boardService.write(board);

        //then
        Board findPost = boardService.boardView(saveId);
        assertThat(board).isEqualTo(findPost);
    }



    @Test
    void insertDeletePostTest(){
        //given
        Board board = getTestBoard();

        //when
        Integer saveId = boardService.write(board);
        boardService.delete(saveId);

        //then
        assertThat(boardService.boardView(saveId)).isNull();

    }

    @Test
    void deleteExistingPost(){
        //given
        List<Board> boardList = boardService.boardList();
        if(boardList.isEmpty()) return;
        Board lastPosting = boardList.get(0);

        //when
        boardService.delete(lastPosting.getId());

        //then
        assertThat(boardService.boardView(lastPosting.getId())).isNull();
    }
}