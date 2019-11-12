package my.dm.ffxiv.service.logProcessor;

import my.dm.ffxiv.model.FFxivLogContent;
import my.dm.ffxiv.service.LogProcessor;

import java.util.List;

/**
 * 对每个LogContent产生一个ClaSP格式的Input
 * http://www.philippe-fournier-viger.com/spmf/ClaSP.php
 */
public class ClaSPPackager implements LogProcessor {
    @Override
    public List<FFxivLogContent> process(List<FFxivLogContent> logs) {
        return null;
    }
}
