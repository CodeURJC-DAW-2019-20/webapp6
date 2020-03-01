package com.webapp.animeshop;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.webapp.animeshop.JacksonAdapter.JsonPageSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

@Configuration
public class JacksonAdapter implements WebMvcConfigurer {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder().failOnUnknownProperties(false).serializerByType(Page.class,
                new JsonPageSerializer());
    }

    public class JsonPageSerializer extends JsonSerializer<Page> {

        @Override
        public void serialize(Page page, JsonGenerator jsonGen, SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {

            ObjectMapper om = new ObjectMapper().disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            jsonGen.writeStartObject();
            jsonGen.writeFieldName("content");
            jsonGen.writeRawValue(
                    om.writerWithView(serializerProvider.getActiveView()).writeValueAsString(page.getContent()));
            jsonGen.writeEndObject();
        }

    }
}