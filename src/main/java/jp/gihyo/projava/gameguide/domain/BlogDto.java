package jp.gihyo.projava.gameguide.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class BlogDto {
    private Integer id;
    private String title;
    private String text;
    private LocalDate createDate;
    private Integer viewCount;
}
