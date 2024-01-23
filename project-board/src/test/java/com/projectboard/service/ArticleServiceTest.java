package com.projectboard.service;

import com.projectboard.domain.type.SearchType;
import com.projectboard.dto.ArticleDto;
import com.projectboard.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnArticleList(){
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE,"search keyword"); //  제목, 본문, ID, 닉네임, 해시태그

        // Then
        Assertions.assertThat(articles).isNotNull();

    }

    @DisplayName("게시글을 조회하면, 게시글 상세를 반환")
    @Test
    void givenId_whenSearchingArticle_thenReturnArticle(){
        // Given

        // When
        ArticleDto article = sut.searchArticle(1L); //  제목, 본문, ID, 닉네임, 해시태그

        // Then
        Assertions.assertThat(article).isNotNull();
    }
    
}