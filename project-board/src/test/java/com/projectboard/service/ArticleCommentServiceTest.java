package com.projectboard.service;

import com.projectboard.domain.Article;
import com.projectboard.domain.ArticleComment;
import com.projectboard.dto.ArticleCommentDto;
import com.projectboard.repository.ArticleCommentRepository;
import com.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글 Id로 조회하면 , 게시글 댓글 리스트를 가져온다")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnComments(){
        // Given
        Long articleId = 1L;

        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title","content","#java"))
        );

        // When
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        // Then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면 , 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingComment_thenSavesComment(){
        // Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(),"bien",LocalDateTime.now(),"bien","content"));

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));

    }

    @DisplayName("댓글 아이디와 수정 정보를 입력하면, 댓글을 수정한다.")
    @Test
    void givenArticleCommentIdAndModifiedInfo_whenUpdatingArticleComment_thenUpdatesArticleComment(){

        // Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);
        // When
        sut.updateArticleComment(1L, ArticleCommentDto.of(LocalDateTime.now(),"bien",LocalDateTime.now(),"bien","content"));

        //then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    @DisplayName("댓글 ID를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleCommentId_whenDeletingArticleComment_thenDeletesArticleComment(){

        // Given
        BDDMockito.willDoNothing().given(articleCommentRepository).delete(any(ArticleComment.class));
        // When
        sut.deleteArticleComment(1L);

        //then
        then(articleCommentRepository).should().delete(any(ArticleComment.class));
    }


}