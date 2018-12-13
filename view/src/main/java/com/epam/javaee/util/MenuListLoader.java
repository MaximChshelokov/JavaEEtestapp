package com.epam.javaee.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MenuListLoader {
    private final String fileName;

    public MenuListLoader(String fileName) {
        this.fileName = fileName;
    }

    public List<Map<String, String>> getMenuList() {
        List<Map<String, String>> menu = new ArrayList<>();
        try (Scanner file = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (file.hasNextLine()) {
                Map<String, String> map = new HashMap<>();
                map.put("url", file.next());
                map.put("name", file.next());
                menu.add(map);
            }
        } catch (IOException ex) {
            System.err.println("File not found " + fileName);
        }
        return menu;
    }
}
