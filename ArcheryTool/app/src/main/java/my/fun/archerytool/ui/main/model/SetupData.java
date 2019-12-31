package my.fun.archerytool.ui.main.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 某次剪切的配置model
 */
public class SetupData {

    public String orgFilePath = null;
    public List<CutSetup> cutSetupList = new ArrayList<>();
    public Integer fixDuration;//固定的剪切长度

}
