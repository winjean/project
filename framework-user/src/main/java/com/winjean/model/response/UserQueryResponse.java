package com.winjean.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserQueryResponse {

    @JacksonXmlProperty
    private String id;

    @JacksonXmlProperty
    private String name;

    @JsonFormat(/*locale="zh", timezone="GMT+8",*/ pattern="yyyy-MM-dd HH:mm:ss")
    @JacksonXmlProperty(localName = "biday")
    @JsonProperty(value = "bd")
    private Date birthday;

    @JacksonXmlProperty
    private String sex;

    @JacksonXmlProperty
    private String email;
}
