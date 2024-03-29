package com.sc.community.model;

import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 17:332019/8/28
 * @Description:
 */
@Data
public class Question {
    private Integer id;
    private String description;
    private String title;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;
    private String tag;
}
