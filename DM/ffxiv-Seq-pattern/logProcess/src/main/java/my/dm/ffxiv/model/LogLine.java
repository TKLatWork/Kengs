package my.dm.ffxiv.model;

import lombok.Data;
import my.dm.ffxiv.Config;
import my.dm.ffxiv.LogConst;

/**
 * 代表一行日志
 */
@Data
public class LogLine {

    public LogLine(String content){
        this.content = content;
        String[] logParts = content.split(Config.LOG_CONTENT_SPL);
        //内容初始化
        if(logParts != null && logParts.length > 2){
            loadCommon(logParts);
            switch (typeId){
                case LogConst.TYPE_ID_01: loadTypeId01(logParts);break;
                case LogConst.TYPE_ID_21: loadTypeId21(logParts);break;
            }
        }
    }

    //行序号
    String content;

    //内容
    String typeId;
    String time;
    String casterName;
    String abilityId;
    String abilityName;
    String fieldName;

    private void loadCommon(String[] logParts){
        this.typeId = logParts[0];
        this.time = logParts[1];
    }

    private void loadTypeId01(String[] logParts){
        this.fieldName = logParts[3];
    }

    private void loadTypeId21(String[] logParts){
        this.casterName = logParts[3];
        this.abilityId = logParts[4];
        this.abilityName = logParts[5];
    }

}
