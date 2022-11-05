package org.example.sdk.common.log;

/**
 * 文本分隔符
 */
public enum TextDelimiter {

    COMMA(","), //逗号分隔符
    VERTICAL_BAR("|"); //竖线分隔符

    /**
     * 分隔符
     */
    private String delimiter;

    /**
     * 构造方法
     * @param delimiter 分隔符
     */
    private TextDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
