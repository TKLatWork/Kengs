package my.dm.ffxiv.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理配置，没有表示不限制
 */
@Data
public class LogProcessConfig {

    //最小
    Long logSizeMin = -1l;
    //场地，战斗名
    List<String> fieldNames = new ArrayList<String>();
    //日志行的头编码
    List<String> logTypeIds = new ArrayList<String>();
    //施法者名
    List<String> casterNames = new ArrayList<String>();
    //能力名，不是以下的
    List<String> abilityNamesNot = new ArrayList<String>();
    
}
