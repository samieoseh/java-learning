package com.samuel.example.services.impl;

import com.samuel.example.services.*;
import org.springframework.stereotype.Component;

@Component
public class ColourPrinterImpl implements ColourPrinter {
    private final RedPrinter redPrinter;
    private final BluePrinter bluePrinter;
    private final GreenPrinter greenPrinter;

    public ColourPrinterImpl(RedPrinter redPrinter, GreenPrinter greenPrinter, BluePrinter bluePrinter) {
        this.redPrinter = redPrinter;
        this.bluePrinter = bluePrinter;
        this.greenPrinter = greenPrinter;
    }

    @Override
    public  String print() {
        return String.join(", ", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
    }
}
