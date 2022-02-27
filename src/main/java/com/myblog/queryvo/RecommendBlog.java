package com.myblog.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecommendBlog {
    private Long id;
    private String title;
    private String firstPicture;
    private boolean recommend;
}
