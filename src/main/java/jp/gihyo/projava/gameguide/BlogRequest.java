package jp.gihyo.projava.gameguide;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class BlogRequest {

    @NotBlank(message = "タイトルを記入して下さい。")
    private String title;

    @NotBlank(message = "内容を記入してください。")
    private String contents;

    private Integer id;

}
