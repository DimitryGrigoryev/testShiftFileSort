package ru.cft.shift.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.cft.shift.config.validate.PathValidateCheck;
import ru.cft.shift.config.validate.ShortAndFullAreMutualExclusive;

import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@FieldDefaults(level = PRIVATE)
@Parameters(parametersValidators = PathValidateCheck.class)
public class ExportModeArgs {
    @Parameter(names = "-a", description = "Write method. added or overwriting")
    boolean saveAddingMode = false;

    @Parameter(names = "-o" , description = "outputDirectory")
    String outputDirectory = "export/";

    @Parameter(names = "-p" , description = "prefixName")
    String prefixName = "";

    @Parameter(names = {"--help", "-h"}, description = "Displays information about this application. Ignoring other options", help = true)
    private boolean help;

    @Override
    public String toString() {
        return "ExportModeArgs{" +
                "saveAddingMode=" + saveAddingMode +
                ", outputDirectory='" + outputDirectory + '\'' +
                ", prefixName='" + prefixName + '\'' +
                ", help=" + help +
                '}';
    }
}
