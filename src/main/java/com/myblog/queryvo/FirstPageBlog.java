package com.myblog.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FirstPageBlog {
    //博客信息
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;

    //分类名称
    private String typeName;

    //用户名
    private String nickname;
    //用户头像
    private String avatar;
}
