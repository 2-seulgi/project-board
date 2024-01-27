package com.projectboard.service;

import com.projectboard.domain.Article;
import com.projectboard.domain.type.SearchType;
import com.projectboard.dto.ArticleDto;
import com.projectboard.dto.ArticleUpdateDto;
import com.projectboard.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSaveArticle(){

        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // When
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(),"bien","title","content","#java"));

        //then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 아이디와 수정 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle(){

        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // When
        sut.updateArticle(1L, ArticleUpdateDto.of("title","content","#java"));

        //then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle(){

        // Given
        BDDMockito.willDoNothing().given(articleRepository).delete(any(Article.class));
        // When
        sut.deleteArticle(1L);

        //then
        then(articleRepository).should().delete(any(Article.class));
    }

}