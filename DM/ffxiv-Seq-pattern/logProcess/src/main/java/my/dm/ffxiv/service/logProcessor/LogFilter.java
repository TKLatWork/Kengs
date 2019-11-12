package my.dm.ffxiv.service.logProcessor;

import my.dm.ffxiv.LogConst;
import my.dm.ffxiv.model.FFxivLogContent;
import my.dm.ffxiv.model.LogLine;
import my.dm.ffxiv.service.LogProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static my.dm.ffxiv.LogConst.Ability_Id_Attack;

/**
 * 过滤出21 施法log, 除了平A
 */
public class LogFilter implements LogProcessor {

    private static Logger LOG = Logger.getLogger(LogFilter.class.getName());

    @Override
    public List<FFxivLogContent> process(List<FFxivLogContent> logs) {
        List<FFxivLogContent> resultLogs = new ArrayList<>();

        for (FFxivLogContent logContent: logs) {
            resultLogs.add(process(logContent));
        }
        return resultLogs;
    }

    private FFxivLogContent process(FFxivLogContent log){

        List<LogLine> logLines =log.getLogLines().stream()
                .filter(l-> LogConst.TYPE_ID_21.equals(l.getTypeId()))
                .filter(l-> !Ability_Id_Attack.equals(l.getAbilityId()))
                .collect(Collectors.toList());

        LOG.info("LogFilter: " + log.getName() + ", resultSize:" + logLines.size());
        log.setLogLines(logLines);
        return log;
    }

}
