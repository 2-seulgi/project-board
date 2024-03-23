package com.projectboard.dto;

import com.projectboard.domain.Article;
import com.projectboard.domain.UserAccount;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.projectboard.domain.Article}
 */

/**
 * record 는 불변 데이터를 나타내는 방법을 제공
 * 간단하게 필드를 정의하고, 해당 필드에 대한 기본 생성자, getter, equals(), hashCode(), toString() 자동생성
 * 필요에 따라 추가적인 생성자나 메서드 정의 가능
 */
public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleDto(null, userAccountDto, title, content, hashtag, null, null, null, null);
    }
    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Article toEntity(UserAccount userAccount) {
        return Article.of(
                userAccount,
                title,
                content,
                hashtag
        );
    }
}
