package com.samuel.example.services.impl;

import com.samuel.example.services.BluePrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishBluePrinter implements BluePrinter {
    @Override
    public  String print() {
        return "blue";
    }

}
