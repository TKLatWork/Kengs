package my.dm.ffxiv.model;

import lombok.Data;

import java.io.File;
import java.util.List;

import static my.dm.ffxiv.LogConst.TYPE_ID_01;

/**
 * 代表一份LOG，或者说一场战斗的LOG
 */
@Data
public class FFxivLogContent {

    public FFxivLogContent(File file, List<LogLine> logLines){
        this.file = file;
        this.name = file.getName();
        this.logLines = logLines;
        for (LogLine line: logLines) {
            //找01类型，场地LOG
            if(line.typeId.equals(TYPE_ID_01)){
                fieldName = line.getFieldName();
                break;
            }
        }
    }

    public FFxivLogContent(String name, List<LogLine> logLines){
        this.name = name;
        this.logLines = logLines;
    }


    //日志文件名
    private File file = null;
    private String name;
    //场地，战斗名
    private String fieldName = null;
    private List<LogLine> logLines;

    public File getFile(){
        return file;
    }
}
