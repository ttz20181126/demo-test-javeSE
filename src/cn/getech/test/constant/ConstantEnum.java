package cn.getech.test.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ConstantEnum {

    //public static final String constantString = "1"; 枚举类提供更多的获取values集合、排序等

    ZERO_STRING("0","字符串常量0"),
    ONE_STRING("1","返工工单"),
    THREE_STRING("3","改配工单"),
    ;

    private String value;
    private String msg;
}