package com.fiireball.maven.fiirebot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	protected static String privateKey;
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
			Scanner reader = new Scanner(new File("G:\\Java Projects\\FiireBot\\workspace\\com.fiireball.maven.fiirebot\\.privateKey.txt"));
			privateKey = reader.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR] privateKey could not be read.");
			e.printStackTrace();
		}
        if (!privateKey.isEmpty()) {
        	Bot.create(privateKey);
        }
        
    }
}
