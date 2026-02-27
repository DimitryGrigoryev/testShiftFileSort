package ru.cft.shift;

import ru.cft.shift.service.FileFilter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileFilter.start(args);
    }
}