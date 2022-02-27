package com.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FriendLink implements Serializable {
    private Long id;
    private String blogname;
    private String blogaddress;
    private String pictureaddress;
    private Date createTime;
}
