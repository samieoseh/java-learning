//package com.samuel.example;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ExampleApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ExampleApplication.class, args);
//	}
//
//}

package com.samuel.example;

import com.samuel.example.services.impl.ColourPrinterImpl;
import com.samuel.example.services.ColourPrinter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication implements CommandLineRunner {

	private ColourPrinter colourPrinter;

	public ExampleApplication(ColourPrinter colourPrinter) {
		this.colourPrinter = colourPrinter;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}
	@Override
	public void run(final String... args) {
		System.out.println(colourPrinter.print());
	}
}

