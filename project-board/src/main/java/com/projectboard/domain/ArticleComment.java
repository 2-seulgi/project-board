package com.projectboard.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Long article_id; // 게시글 ID
    private String content; // 내용
    private LocalDateTime createdAt; //생성일시
    private String createdBy; //생성자
    private LocalDateTime modifiedAt; // 수행일시
    private String modifiedBy; // 수행자
}
