package com.samuel.example.services.impl;

import com.samuel.example.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishRedPrinter implements RedPrinter {
    @Override
    public  String print() {
        return "red";
    }
}
