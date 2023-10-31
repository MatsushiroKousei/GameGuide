package jp.gihyo.projava.gameguide;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class BlogRequest {

    /*
    @NotBlank ->空白も×
    @NotNull　->nullのみ×
    @NotEmpty ->空白のみ〇
    Validationで制限する文字、これらをtitleとcontentsに付与することにより、投稿に制限を掛ける
     */

    @NotBlank(message = "※タイトルを記入して下さい。")
    @Size(min = 5, message = "5文字以上記入してください。")
    private String title;

    @NotBlank(message = "※内容を記入してください。")
    @Size(min = 30,message = "30文字以上記入してください。")
    private String contents;

    private Integer id;

}
