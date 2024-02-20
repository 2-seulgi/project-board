package com.projectboard.repository;

import com.projectboard.domain.Article;
import com.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.domain.Page;


@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserAccount_NicknameContaining(String nickName, Pageable pageable);
    Page<Article> findByHashtag(String hashtag, Pageable pageable);



    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        // 모든 컬럼이 아닌 선택적으로 검색 허용함
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // like '%%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // like '%%'
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%%'


        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like ''


    }
}
