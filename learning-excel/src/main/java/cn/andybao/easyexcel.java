package cn.andybao;

import cn.andybao.listener.ExcelReadListener;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.exception.ExcelCommonException;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AndyBao
 * @description
 * @create_at 2022/11/30 11:22
 * @since
 */
@Slf4j
public class easyexcel {

    /**
     * 文件格式校验
     *
     * @param file
     * @return
     */
    public static Boolean fileFormatJudgment(MultipartFile file) {
        //判断文件是否存在
        if (null == file) {
            log.error("文件不存在！");
            throw new ExcelCommonException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        assert fileName != null;
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            log.error(fileName + "不是excel文件");
            throw new ExcelCommonException("不是excel文件");
        }
        return true;
    }

    /**
     * 通用读取
     *
     * @param file
     * @return
     */
    public List<T> general(MultipartFile file) {
        if (Boolean.FALSE.equals(fileFormatJudgment(file))) {
            throw new ExcelCommonException("请检查文件格式！");
        }
        List<T> responce;
        try {
            List<T> t = EasyExcelFactory.read(file.getInputStream(), Object.class, new ExcelReadListener()).sheet().doReadSync();
            if (CollectionUtils.isEmpty(t)) {
                throw new ExcelCommonException("读取 Excel 文件异常！请检查文件");
            }
            responce = t.stream().distinct().collect(Collectors.toList());
        } catch (IOException e) {
            log.error("=>=>=>IO流接收文件失败！", e);
            throw new ExcelCommonException("接收 Excel 文件失败！");
        }
        return responce;
    }

    /**
     * 通用 Excel 单表导出
     *
     * @param request  要导出的数据
     * @param fileName 文件名
     * @param clazz    标签栏
     * @param response 输出流
     */
    public void generaExport(@RequestBody List<T> request, String fileName, Class<T> clazz, HttpServletResponse response) {
        if (CollectionUtils.isEmpty(request)) {
            throw new ExcelCommonException("无可导出数据");
        }
        try {
            EasyExcelFactory.write(exportfileFormat(response, fileName))//固定文件头
                    .build()//新建 Excel 文件 面板
                    .write(request, EasyExcelFactory.writerSheet(fileName)
                            .head(clazz)//标签栏
                            .build())
                    .finish();
        } catch (Exception e) {
            response.reset();
            log.error("导出失败 \n:{}", e.getMessage());
            throw new ExcelCommonException("导出失败");
        }
    }

    /**
     * 固定文件头
     *
     * @param response
     * @return
     * @throws IOException
     */
    public ServletOutputStream exportfileFormat(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        if (StringUtils.isEmpty(fileName)) {
            fileName = URLEncoder.encode("HARMAY", "UTF-8").replace("\\+", "%20");
        }
        fileName = URLEncoder.encode(fileName, "UTF-8").replace("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        return response.getOutputStream();
    }
}
