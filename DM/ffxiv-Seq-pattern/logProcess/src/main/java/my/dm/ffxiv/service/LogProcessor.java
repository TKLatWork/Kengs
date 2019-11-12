package my.dm.ffxiv.service;

import my.dm.ffxiv.model.FFxivLogContent;

import java.util.List;

/**
 * 要求无状态,返回不空时会覆盖当前Manager的logsDataList
 */
public interface LogProcessor {

    List<FFxivLogContent> process(List<FFxivLogContent> logs);

}
