package io.kpatel.algbeans.cli;

import com.beust.jcommander.IStringConverter;
import io.kpatel.algbeans.CleaningPolicies;
import io.kpatel.algbeans.CleaningPolicy;

/**
 *  JCommander helper: Parses Cleaning Policies from commandline.
 */
public class CleaningPolicyConverter implements IStringConverter<CleaningPolicy> {
    @Override
    public CleaningPolicy convert(String value) {
        switch (value.trim().toLowerCase()) {
            case "always": return CleaningPolicies.ALWAYS;
            case "never": return CleaningPolicies.NEVER;
            case "never-cwd": return CleaningPolicies.NEVER_CWD;
            default: throw new IllegalArgumentException("Cleaning policy must be one of the following: 'always', 'never', 'never-cwd' '");
        }
    }
}
