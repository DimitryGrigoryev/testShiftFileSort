package ru.cft.shift.service;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import ru.cft.shift.config.ExportModeArgs;
import ru.cft.shift.config.InputFileForFilterArgs;
import ru.cft.shift.config.TypeStatArgs;
import ru.cft.shift.service.util.UtilityFilesFilters;

import java.io.IOException;

public class FileFilter {
    public static void start(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Exit. There must be at least one incoming file.");
            return;
        }

        TypeStatArgs typeStatArgs = new TypeStatArgs();
        InputFileForFilterArgs inputFileForFilterArgs = new InputFileForFilterArgs();
        ExportModeArgs exportModeArgs = new ExportModeArgs();
        JCommander jc = JCommander.newBuilder()
                .addObject(new Object[]{inputFileForFilterArgs, typeStatArgs, exportModeArgs})
                .build();
        try {
            jc.parse(args);
        } catch (ParameterException e) {
            System.err.println(e.getLocalizedMessage());
            jc.usage();
            return;
        }

        UtilityFilesFilters utilityFilesFilters = new UtilityFilesFilters();
        if (exportModeArgs.isHelp()) {
            jc.usage();
            return;
        }
        if (inputFileForFilterArgs.getFiles().isEmpty()){
            System.out.println("Exit. There must be at least one incoming file.");
            return;
        }
        utilityFilesFilters.parseFiles(inputFileForFilterArgs);
        utilityFilesFilters.exportFilterResults(typeStatArgs, exportModeArgs);
    }
}
