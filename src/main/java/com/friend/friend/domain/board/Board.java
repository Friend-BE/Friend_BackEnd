package com.friend.friend.domain.board;

import com.friend.friend.common.BaseEntity;
import com.friend.friend.dto.NoticeRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor
@Getter
@Setter
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;   //제목
    @Column(columnDefinition = "TEXT")
    private String body;    //본문
    private String author;    //작성자



}