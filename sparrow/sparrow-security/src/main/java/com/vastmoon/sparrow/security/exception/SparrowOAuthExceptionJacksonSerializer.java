package com.vastmoon.sparrow.security.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;

/**
 * <p> ClassName: CustomOAuthExceptionJacksonSerializer
 * <p> Description: json处理类
 *
 * @author yousuf 2020/11/6
 */
public class SparrowOAuthExceptionJacksonSerializer extends StdSerializer<SparrowOAuth2Exception> {
    private static final long serialVersionUID = -3021318669201199877L;
    protected SparrowOAuthExceptionJacksonSerializer() {
        super(SparrowOAuth2Exception.class);
    }

    @Override
    public void serialize(SparrowOAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("code", value.getOAuth2ErrorCode());
        String errorMessage = value.getMessage();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
        gen.writeStringField("message", errorMessage);
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }
}
