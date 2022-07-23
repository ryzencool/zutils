package com.marsh.zutils.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.SneakyThrows;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: Colin
 * @describer: 操作excel工具类
 * @date: 2021/4/26  11:47
 */
public class ExcelUtils {


    private static List<List<Object>> lineList = new ArrayList<>();

    /**
     * excel 导出工具类
     *
     * @param response
     * @param fileName    文件名
     * @param data    数据源
     * @param fieldAndAlias 导出的excel中的列名  对象中的字段名字
     * @throws IOException
     */

    @SneakyThrows
    public static void export(HttpServletResponse response, String fileName, Map<String, String> fieldAndAlias, List<?> data) {
        ExcelWriter bigWriter = ExcelUtil.getBigWriter(String.valueOf(true));
        bigWriter.setHeaderAlias(fieldAndAlias);
        bigWriter.setOnlyAlias(true);
        bigWriter.setFreezePane(1);
        bigWriter.write(data, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + DateUtil.now().format(DateTimeFormatter.ofPattern(DateUtil.LINK_DATE_TIME_PATTERN))+".xlsx").getBytes(), StandardCharsets.ISO_8859_1));
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            bigWriter.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bigWriter.close();
        }
        IoUtil.close(out);
    }
}
