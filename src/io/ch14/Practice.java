package io.ch14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Practice {

    public static void main(String[] args) {


        try (FileInputStream in = new FileInputStream("b.txt")) {

            int realData;
            while ((realData = in.read()) != -1){
                System.out.print((char) realData);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    } // end of main
}
