package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public Integer write(Board board){
        boardRepository.save(board);
        return board.getId();
    }

    public void delete(Integer id){
        boardRepository.deleteById(id);
    }


    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    //특정 게시글 불러오기
    public Board boardView(Integer id){

        return boardRepository.findById(id).orElse(null);
    }


}
