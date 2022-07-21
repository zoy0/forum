package com.forum.projectlzy.entity;

import com.forum.projectlzy.constant.HttpCodeContant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结果集返回对象
 */

@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {

    private Integer code;

    private String message;

    private Object data;

    public static ResultDto buildResultDto(Integer code, String message, Object data) {
        return new ResultDto(code, message, data);
    }

    public static ResultDto buildSuccessResultDto(String message, Object data) {
        return buildResultDto(HttpCodeContant.SUCCESS, message, data);
    }

    public static ResultDto buildErrorResultDto(Integer code, String message) {
        return buildResultDto(code, message, null);
    }

}
