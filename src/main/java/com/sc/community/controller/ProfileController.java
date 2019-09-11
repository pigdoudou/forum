package com.sc.community.controller;

import com.sc.community.model.User;
import com.sc.community.service.NoticeService;
import com.sc.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: An
 * @Date: Created in 16:552019/8/31
 * @Description:
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(value = "action", required = false) String action,
                          Model model,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            questionService.myList(user.getId(), page, size);
            model.addAttribute("pagination", questionService.myList(user.getId(), page, size));
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            noticeService.list(user.getId(),page,size);
            model.addAttribute("pagination",noticeService.list(user.getId(),page,size));
        }
        return "profile";
    }
}
