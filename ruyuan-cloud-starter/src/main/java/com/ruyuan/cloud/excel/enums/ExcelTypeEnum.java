package com.ruyuan.cloud.excel.enums;

/**
 * Excel 类型枚举
 */
public enum ExcelTypeEnum {

    /**
     * 枚举
     */
    XLS("xls"),
    XLSX("xlsx");

    private String value;

    ExcelTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}