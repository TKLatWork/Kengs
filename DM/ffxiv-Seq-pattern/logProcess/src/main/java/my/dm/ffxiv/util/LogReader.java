package my.dm.ffxiv.util;

import my.dm.ffxiv.Config;
import my.dm.ffxiv.model.FFxivLogContent;
import my.dm.ffxiv.model.LogLine;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 日志读取
 */
public class LogReader {

    private static Logger LOG = Logger.getLogger(LogReader.class.getName());

    public static List<FFxivLogContent> loadFolder(String folderPath){
        List<File> files = searchFolder(folderPath);
        List<FFxivLogContent> logContents = files.stream()
                .map(LogReader::loadFile).collect(Collectors.toList());
        return logContents;
    }

    //实际读取一个LOG文件
    public static FFxivLogContent loadFile(File file){
        FFxivLogContent result = null;
        List<LogLine> logLines = null;
        //读取全部行
        try (FileReader reader = new FileReader(file)) {
            logLines = IOUtils.readLines(reader)
                    .stream()
                    .map(LogLine::new)
                    .collect(Collectors.toList());
            LOG.info("load file:" + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
            LOG.severe(e.getMessage());
        }
        //组装
        result = new FFxivLogContent(file, logLines);
        return result;
    }

    //准备遍历
    public static List<File> searchFolder(String folderPath){
        List<File> logFiles = null;
        try (Stream<Path> walk = Files.walk(Paths.get(folderPath))) {
            logFiles = walk
                    .filter(Files::isRegularFile)
                    .filter(f->FilenameUtils.isExtension(f.getFileName().toString(), ".log"))
                    .map(x->x.toFile())
                    .collect(Collectors.toList());
            logFiles.forEach(f->LOG.info("find in folder:" + f.getName()));
        } catch (IOException e) {
            e.printStackTrace();
            LOG.severe(e.getMessage());
        }
        return logFiles;
    }

}
