package io.kpatel.algbeans.cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CleaningPolicyValidator implements IParameterValidator {
    private static Set<String> validPolicies = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList("always", "never", "never-cwd"))
    );

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!validPolicies.contains(value.trim().toLowerCase())) {
            throw new ParameterException(String.format(
                    "Parameter %s must be one of the following: 'always', 'never', 'never-cwd' '", name
            ));
        }
    }
}
