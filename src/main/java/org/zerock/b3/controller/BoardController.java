package org.zerock.b3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b3.dto.BoardDTO;
import org.zerock.b3.dto.PageRequestDTO;
import org.zerock.b3.dto.PageResponseDTO;
import org.zerock.b3.service.BoardService;

import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void registerGET(){}

    @PostMapping("/register")
    public String registerPOST(@Valid BoardDTO boardDTO, BindingResult bindingResult,RedirectAttributes rttr){

        log.info("board register"+boardDTO);
        if (bindingResult.hasErrors()){
            log.info("has errors.....");
            rttr.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        Integer bno = boardService.register(boardDTO);
        rttr.addFlashAttribute("result",bno);

        return "redirect:/board/list";
    }

}
