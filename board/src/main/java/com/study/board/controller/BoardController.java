package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "boardwrite";
    }

    @PostMapping("/board/writeproc")
    public String boardWriteProc(Board board, Model model){

        boardService.write(board);

        model.addAttribute("message", "글이 작성되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }
    @PostMapping("/board/modifyproc/{id}")
    public String boardModifyProc(@PathVariable("id") Integer id, Board board){
        Board tempBoard = boardService.boardView(id);
        tempBoard.setTitle(board.getTitle());
        tempBoard.setContent(board.getContent());

        boardService.write(tempBoard);
        return "redirect:/board/list";
    }
    @GetMapping("/board/delete")
    public String boardDeleteProc(Integer id){
        boardService.delete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/update/{id}")
    public String boardUpdateProc(@PathVariable("id") Integer id,
                                  Model model) {
        model.addAttribute("board", boardService.boardView((id)));
        return "boardmodify";
    }


    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword){
        Page<Board> list;
        if(searchKeyword==null) {
            list =  boardService.boardList(pageable);

        }else{
            list = boardService.boardSearchList(searchKeyword, pageable);
        }
        model.addAttribute("list", list);

        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage-4, 1);
        int endPage = Math.min(nowPage+5, list.getTotalPages());

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "boardlist";
    }
    @GetMapping("/board/view")
    public String boardView(Model model, Integer id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }


}
