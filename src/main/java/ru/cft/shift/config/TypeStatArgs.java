package ru.cft.shift.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.cft.shift.config.validate.ShortAndFullAreMutualExclusive;

import static lombok.AccessLevel.*;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@Parameters(parametersValidators = ShortAndFullAreMutualExclusive.class)
public class TypeStatArgs {
    @Parameter(names = "-s", description = "Short type of statistics collection")
    boolean shortType;

    @Parameter(names = "-f", description = "Full type of statistics collection")
    boolean fullType;

    @Override
    public String toString() {
        return "TypeStatArgs{" +
                "shortType=" + shortType +
                ", fullType=" + fullType +
                '}';
    }
}
