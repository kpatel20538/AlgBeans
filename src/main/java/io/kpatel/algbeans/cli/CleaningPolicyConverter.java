package io.kpatel.algbeans.cli;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import io.kpatel.algbeans.CleaningPolicy;

/**
 *  JCommander helper: Parses Cleaning Policies from commandline.
 */
public class CleaningPolicyConverter implements IStringConverter<CleaningPolicy> {


    @Override
    public CleaningPolicy convert(String value) {
        switch (value.trim().toLowerCase()) {
            case "always": return CleaningPolicy.ALWAYS;
            case "never": return CleaningPolicy.NEVER;
            case "never-cwd": return CleaningPolicy.NEVER_CWD;
            default: throw new IllegalArgumentException("Cleaning policy must be one of the following: 'always', 'never', 'never-cwd' '");
        }
    }
}
