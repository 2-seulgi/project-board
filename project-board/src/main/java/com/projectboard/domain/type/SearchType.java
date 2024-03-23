package com.projectboard.domain.type;

import lombok.Getter;

@Getter
public enum SearchType {
    TITLE("제목"),
    CONTENT("본문"),
    HASHTAG("해시태그"),
    ID("유저 ID"),
    NICKNAME("닉네임");

    SearchType(String description) {
        this.description = description;
    }

    private final String description;
}
