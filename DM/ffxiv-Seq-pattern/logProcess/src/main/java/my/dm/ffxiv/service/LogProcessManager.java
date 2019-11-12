package my.dm.ffxiv.service;

import my.dm.ffxiv.model.FFxivLogContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 统筹控制处理流程
 */
public class LogProcessManager {

    private static Logger LOG = Logger.getLogger(LogProcessManager.class.getName());

    private List<FFxivLogContent> logDataList = new ArrayList<>();
    private List<LogProcessor> processors = new ArrayList<>();

    public LogProcessManager setup(List<FFxivLogContent> orgLogDatas, LogProcessor ... processorsInOrder){
        this.logDataList = orgLogDatas;
        this.processors = Arrays.stream(processorsInOrder).collect(Collectors.toList());
        return this;
    }

    public void beginProcess(){
        LOG.info("begin log processing");

        for (LogProcessor p: processors) {
            List<FFxivLogContent> resultLogDatas = p.process(logDataList);
            if(resultLogDatas != null){
                logDataList = resultLogDatas;
            }
        }
    }

}
