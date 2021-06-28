package com.shouguouo.base.design;

/**
 * @author swj
 * @date 2018/2/7
 */
public class Token {
    public enum TokenType{
        LPAR, RPAR,
        PLUS,
        MINUS,
        DIV,
        INT,
        NONE,
    }
    public TokenType tokenType;
    public Object value;

    public Token(TokenType tt, Object v){
        this.tokenType = tt;
        this.value = v;
    }
}
