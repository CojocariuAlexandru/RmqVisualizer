package com.RmqVisualizer.RmqVisualizer;

import com.RmqVisualizer.RmqVisualizer.test.ApplicationTest;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RmqVisualizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmqVisualizerApplication.class, args);
//		JUnitCore junit = new JUnitCore();
//		junit.addListener(new TextListener(System.out));
//		junit.run(ApplicationTest.class);
	}

}
