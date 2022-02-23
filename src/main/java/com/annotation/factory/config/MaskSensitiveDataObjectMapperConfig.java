package com.annotation.factory.config;

import com.annotation.factory.annotation.MaskSensitiveData;
import com.annotation.factory.introspector.MaskSensitiveDataAnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnClass(MaskSensitiveData.class)
public class MaskSensitiveDataObjectMapperConfig {

    @Primary
    @Autowired
    public void objectMapper(ObjectMapper objectMapper) {
        AnnotationIntrospector serializationAnnotationIntrospector
                = AnnotationIntrospector.pair(objectMapper.getSerializationConfig().getAnnotationIntrospector(), new MaskSensitiveDataAnnotationIntrospector());
        objectMapper.setAnnotationIntrospectors(serializationAnnotationIntrospector, objectMapper.getDeserializationConfig().getAnnotationIntrospector());
    }
}
