package com.marsh.zutils.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.Map;

/**
 * xml utils
 *
 * @author lucky.zhou
 */
public class XmlUtil {

    private XmlUtil() {

    }

    /**
     * xml to map： constraint one level xml
     *
     * @return Map
     */
    public static Map<String, Object> xmlToMap(String content) throws IOException {
        ObjectMapper om = new XmlMapper();
        return om.readValue(content, new TypeReference<Map<String, Object>>() {
        });
    }
}
