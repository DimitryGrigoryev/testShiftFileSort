package ru.cft.shift.config.validate;

import com.beust.jcommander.IParametersValidator;
import com.beust.jcommander.ParameterException;

import java.util.Map;
import java.util.regex.Pattern;

public class PathValidateCheck implements IParametersValidator {
    @Override
    public void validate(Map<String, Object> parameters) throws ParameterException {

        try {
            Pattern pattern = Pattern.compile("[^0-9a-zA-Z-._/]+");
            String paramExportPath = parameters.get("-o").toString();
            String paramPrefixName = parameters.get("-p").toString();
            if(pattern.matcher(paramExportPath).find()) {
                throw new ParameterException("-o option error. the path outputs incorrect: " + paramExportPath + "\nThe work is over");
            }
            if(pattern.matcher(paramPrefixName).find()) {
                throw new ParameterException("-p option error. the prefix name incorrect: " + paramPrefixName + "\nThe work is over");
            }
        } catch (ParameterException e) {
            throw new ParameterException(e.getMessage());
        }
    }
}
