package com.example.springmvc;

import com.example.springmvc.component.EnumConvertFactory;
import com.example.springmvc.component.LoginUserParamResolver;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Priority;
import java.util.List;

/**
 * spring mvc自定义配置
 *
 * @author xiongLiang
 */

@Configuration
@EnableWebMvc
@Priority(Integer.MAX_VALUE)
public class SpringMvcConfig implements WebMvcConfigurer, ApplicationContextAware {

    // 添加自定义格式化器和转化器
    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public EnumConvertFactory enumConvertFactory() {
        return new EnumConvertFactory();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(enumConvertFactory());
    }
    // 添加自定义参数解析器

    @Bean
    public LoginUserParamResolver loginUserParamResolver() {
        return new LoginUserParamResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserParamResolver());
    }


    /**
     * 配置自定义json转换器
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().filter(e -> e instanceof MappingJackson2HttpMessageConverter).findFirst().ifPresent(this::configJacksonConverter);
    }

    private void configJacksonConverter(HttpMessageConverter converter) {
        ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
        // 序列化设置
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 反序列化设置
        DeserializationConfig deserializationConfig = objectMapper.getDeserializationConfig();
        objectMapper.setConfig(deserializationConfig.without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).with(DeserializationFeature.READ_ENUMS_USING_TO_STRING));
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        applicationContext.getBeansOfType(HandlerInterceptor.class).forEach((key, value) -> registry.addInterceptor(value));
    }
}
