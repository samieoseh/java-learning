package com.samuel.example.services.impl;

import com.samuel.example.services.GreenPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishGreenPrinter implements GreenPrinter {
    @Override
    public  String print() {
        return "green";
    }
}
