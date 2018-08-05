package com.example.springmvc.component;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 字符串转枚举
 *
 * @author xiongLiang
 * @date 2018/7/19 11:14
 */
public class EnumConvertFactory implements ConverterFactory<String, Enum> {
    private static final Logger logger = LoggerFactory.getLogger(EnumConvertFactory.class);

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new String2EnumConverter(targetType);
    }

    class String2EnumConverter<T extends Enum<T>> implements Converter<String, T> {

        private Class<T> enumType;

        private String2EnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isNotBlank(source)) {
                try {
                    return Enum.valueOf(enumType, source);
                } catch (Exception e) {
                    logger.info("convert string parameter:{} to enum:{} fail", source, enumType.getName(),e);
                }
            }
            return null;
        }
    }
}
