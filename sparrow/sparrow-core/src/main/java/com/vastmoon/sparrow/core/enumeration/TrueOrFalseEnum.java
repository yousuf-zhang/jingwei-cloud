package com.vastmoon.sparrow.core.enumeration;

/**
 * <p> ClassName: TrueOrFalseEnum
 * <p> Description: true or false 枚举
 *
 * @author yousuf 2020/2/19
 */
public enum  TrueOrFalseEnum implements Enumerator<Integer> {
    /**true or false 枚举*/
    TRUE(1, "true"),
    FALSE(0, "false");

    private final int code;
    private final String text;

    TrueOrFalseEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getText() {
        return this.text;
    }

    public static TrueOrFalseEnum parse(Boolean code) {
        return code ? TrueOrFalseEnum.TRUE : TrueOrFalseEnum.FALSE;
    }

    public static TrueOrFalseEnum parse(int code) {
        return code == 1 ? TrueOrFalseEnum.TRUE : TrueOrFalseEnum.FALSE;
    }

    public Boolean convert() {
        return this == TrueOrFalseEnum.TRUE ? Boolean.TRUE : Boolean.FALSE;
    }
}
