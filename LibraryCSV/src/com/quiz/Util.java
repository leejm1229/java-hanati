package com.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Util {

	public static boolean isState(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
            	row++;
            }
            if (row <= 1) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        return true;
	}
}
