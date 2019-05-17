package io.kpatel.algbeans.cli;

import com.beust.jcommander.IStringConverter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  JCommander helper: Parses paths from commandline.
 */
public class PathConverter implements IStringConverter<Path> {
    @Override
    public Path convert(String value) {
        return Paths.get(value);
    }
}
