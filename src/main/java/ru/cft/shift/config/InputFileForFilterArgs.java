package ru.cft.shift.config;

import com.beust.jcommander.Parameter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.util.List;

import static lombok.AccessLevel.*;

@Setter
@Getter
@FieldDefaults(level = PRIVATE)
public class InputFileForFilterArgs {
    @Parameter(description = "Files, input one or more", variableArity = true)
    List<File> files;

    @Override
    public String toString() {
        return "InputFileForFilterArgs{" +
                "files=" + files +
                '}';
    }
}
