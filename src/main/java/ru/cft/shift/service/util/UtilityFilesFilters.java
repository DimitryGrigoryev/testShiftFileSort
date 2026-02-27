package ru.cft.shift.service.util;

import com.beust.jcommander.ParameterException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.cft.shift.config.ExportModeArgs;
import ru.cft.shift.config.InputFileForFilterArgs;
import ru.cft.shift.config.TypeStatArgs;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class UtilityFilesFilters {
    final List<String> strings = new ArrayList<>();
    final List<Long> longIntegers = new ArrayList<>();
    final List<BigDecimal> bigDecimals = new ArrayList<>();

    public void parseFiles(InputFileForFilterArgs inputFileForFilterArgs) throws IOException {
        for (File file : inputFileForFilterArgs.getFiles()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        if (this.isLongInteger(line)) {
                            longIntegers.add(Long.parseLong(line));
                        } else if (this.isBigDecimal(line)) {
                            bigDecimals.add(new BigDecimal(line));
                        } else if (this.isString(line)) {
                            strings.add(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    public void exportFilterResults(TypeStatArgs typeStatArgs, ExportModeArgs exportModeArgs) {
        StatisticsCollector collector = new StatisticsCollector();
        File dirFile = new File(exportModeArgs.getOutputDirectory());
        try {
            if (!dirFile.exists()) dirFile.mkdirs();
        } catch (Exception e) {
            System.out.println("Can't create directory: " + exportModeArgs.getOutputDirectory());
        }

        if (!longIntegers.isEmpty()) {
            String FILE_NAME = exportModeArgs.getOutputDirectory() + "/" + exportModeArgs.getPrefixName() + "integers.txt";
            try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, exportModeArgs.isSaveAddingMode())) {
                longIntegers.forEach(longInteger -> {
                    try {
                        fileOutputStream.write(longInteger.toString().concat("\n").getBytes());
                    } catch (IOException e) {
                        System.out.println("Can't write line: " + longInteger.toString().concat("\n"));
                    }
                });
                if (typeStatArgs.isFullType() || typeStatArgs.isShortType()) {
                    collector.collectStatisticsInt(longIntegers, typeStatArgs.isFullType());
                }
            } catch (IOException e) {
                System.out.println("Can't write in file: " + FILE_NAME.concat("\n"));
            }
        }

        if (!bigDecimals.isEmpty()) {
            String FILE_NAME = exportModeArgs.getOutputDirectory() + "/" + exportModeArgs.getPrefixName() + "floats.txt";
            try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, exportModeArgs.isSaveAddingMode())) {
                bigDecimals.forEach(bigDecimal -> {
                    try {
                        fileOutputStream.write(bigDecimal.toString().concat("\n").getBytes());
                    } catch (IOException e) {
                        System.out.println("Can't write line: " + bigDecimal.toString().concat("\n"));
                    }
                });
                if (typeStatArgs.isFullType() || typeStatArgs.isShortType()) {
                    collector.collectStatisticsFloat(bigDecimals, typeStatArgs.isFullType());
                }
            } catch (IOException e) {
                System.out.println("Can't write in file: " + FILE_NAME.concat("\n"));
            }
        }

        if (!strings.isEmpty()) {
            String FILE_NAME = exportModeArgs.getOutputDirectory() + "/" + exportModeArgs.getPrefixName() + "strings.txt";
            try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, exportModeArgs.isSaveAddingMode())) {
                strings.forEach(string -> {
                    try {
                        fileOutputStream.write(string.concat("\n").getBytes());
                    } catch (IOException e) {
                        System.out.println("Can't write line: " + string.concat("\n"));
                    }
                });
                if (typeStatArgs.isFullType() || typeStatArgs.isShortType()) {
                    collector.collectStatisticsString(strings, typeStatArgs.isFullType());
                }
            } catch (IOException e) {
                System.out.println("Can't write in file: " + FILE_NAME.concat("\n"));
            }
        }
    }

    private boolean isBigDecimal(String line) {
        if (line == null) {
            return false;
        }
        try {
            new BigDecimal(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isLongInteger(String line) {
        if (line == null) {
            return false;
        }
        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isString(Object line) {
        return line instanceof String;
    }
}
