package my.dm.ffxiv.service.logProcessor;

import my.dm.ffxiv.model.FFxivLogContent;
import my.dm.ffxiv.service.LogProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 动作里面没有职业信息
 */
public class ByNameCollector implements LogProcessor {

    private static Logger LOG = Logger.getLogger(ByNameCollector.class.getName());

    private Map<String, FFxivLogContent> resultMap;

    @Override
    public List<FFxivLogContent> process(List<FFxivLogContent> logs) {
        resultMap = new HashMap<>();
        logs.forEach(this::process);
        //Map -> List
        List<FFxivLogContent> resultList = resultMap.entrySet().stream()
                .map(set->set.getValue())
                .collect(Collectors.toList());
        return resultList;
    }

    private void process(FFxivLogContent logContent){
        logContent.getLogLines() .forEach(logLine -> {
            String key = logLine.getCasterName();
            if(!resultMap.containsKey(key)){
                resultMap.put(key, new FFxivLogContent(key, new ArrayList<>()));
            }
            resultMap.get(key).getLogLines().add(logLine);
        });
    }

}
