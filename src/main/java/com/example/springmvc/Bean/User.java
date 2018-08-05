package com.example.springmvc.Bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ${<DESCRIPTION>}
 *
 * @author xiongLiang
 * @date 2018/7/29 11:33
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private Sex sex;
    @NotNull(message = "性别不能为空")
    private Integer age;
    private String address;
    private String email;
    private String company;
}
