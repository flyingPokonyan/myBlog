package com.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Picture implements Serializable {
    private Long id;
    private String picturename;
    private String picturetime;
    private String pictureaddress;
    private String picturedescription;
}
