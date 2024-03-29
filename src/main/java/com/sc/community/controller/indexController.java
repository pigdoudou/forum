package com.sc.community.controller;

import com.sc.community.dto.PaginationDTO;
import com.sc.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Auther: An
 * @Date: Created in 11:012019/8/24
 * @Description:
 */
@Controller
public class indexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "5") Integer size,
                        @RequestParam(value = "search",required = false) String search) {
        //分页
        PaginationDTO paginationDTO = new PaginationDTO();
        if (!StringUtils.isBlank(search)) {
            paginationDTO = questionService.search(search, page, size);
            model.addAttribute("search",search);
        } else {
            paginationDTO = questionService.questionList(page, size);
        }
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }
}
