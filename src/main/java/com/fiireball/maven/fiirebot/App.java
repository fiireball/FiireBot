package com.fiireball.maven.fiirebot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	protected static String privateKey;
	private static final Logger logger = LoggerFactory.getLogger(FiireBot.class);
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
			Scanner reader = new Scanner(new File("G:\\Java Projects\\FiireBot\\workspace\\com.fiireball.maven.fiirebot\\.privateKey.txt"));
			privateKey = reader.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("privateKey ({}) could not be read.", privateKey);
			e.printStackTrace();
		}
        if (!privateKey.isEmpty()) {
        	FiireBot.create(privateKey);
        }
        
    }
}
