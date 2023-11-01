package jp.gihyo.projava.gameguide;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BlogRequest {

    @NotBlank
    @Size(min = 5,message = "5文字以上記入してください。")
    private String title;

    @NotBlank
    @Size(min = 10,message = "10文字以上記入してください。")
    private String contents;

    private Integer id;

}
