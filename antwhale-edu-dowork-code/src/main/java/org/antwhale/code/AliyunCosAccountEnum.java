package org.antwhale.code;

public enum AliyunCosAccountEnum {
    getAccessKeyID("LTAI5tGMX6FYXrwze1UpRkWc"),
    getAccessKeySecret("rBYXKgsB0DYnGYh9OWnBod6YoMwJXP"),
    getEndpoint("oss-cn-beijing.aliyuncs.com"),
    loginName("dheduvideo@1912560703600275.onaliyun.com");

    private String value;

    AliyunCosAccountEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
