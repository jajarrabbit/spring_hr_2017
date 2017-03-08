package com.arms.domain.service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by arms20170106 on 17/2/2560.
 */
@Service
public class CreTxtService {
    public  void cre() throws IOException {
        String file = "/home/arms20170106/Desktop/newfile.txt";
        System.out.println("Writing to file: " + file);
        try (BufferedWriter writer = Files.newBufferedWriter( Paths.get(file))  ) {
            writer.write("Java");
            writer.write("Python");
            writer.write("Clojure");
            writer.write("Scala");
            writer.write("JavaScript");
        }
    }
}
