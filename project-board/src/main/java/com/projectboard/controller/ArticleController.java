package com.projectboard.controller;

import com.projectboard.domain.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles(ModelMap map){
        map.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable(name="articleId") long articleId, ModelMap map){
        map.addAttribute("article", "article"); //TODO :: 구현 시 추가
        map.addAttribute("articleComments", List.of());
        return "articles/detail";
    }
}

