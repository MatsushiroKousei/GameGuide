package jp.gihyo.projava.gameguide;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
入れ物
 */

/*--Validation--適切であるかの検証
@NotBlank => 空文字・Null・" "・スペース・タブ（×）
@NotEmpty => スペース・タブ（〇）
@NotNull => Null以外（〇）
 */

@Data
public class BlogRequest {

    @NotBlank(message = "")
    @Size(min = 5,message = "5文字以上記入してください。")
    private String title;

    @NotBlank(message = "")
    @Size(min = 10,message = "10文字以上記入してください。")
    private String contents;

    private Integer id;

}
