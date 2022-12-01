package cn.andybao.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共 Excel 读取监听器
 */
@Slf4j
public class ExcelReadListener extends AnalysisEventListener<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReadListener.class);
    private static final int BATCH_COUNT = 100;// TODO 表格批量处理大小暂限 100
    private List<T> t = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
        t.add(data);
        if (t.size() >= BATCH_COUNT) {
            // 存储完成清理 list
            t = new ArrayList<>();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("解析完成！");
    }

}