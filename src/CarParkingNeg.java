import java.io.*;
import java.util.Scanner;

import dataStructures.ArrayStack;
import dataStructures.Stack;  
public class CarParkingNeg extends ArrayStack { 
    ArrayStack park; 
    int top = 0;
	
    public void input(){
        try {
            BufferedReader input = new BufferedReader(new FileReader("cars.txt"));
            String line;
            while((line = input.readLine()) != null){
                String values[] = line.split(" ");
                process(values[0], values[1]);
            }
            input.close();
        } catch (IOException e) {            
            System.out.println("IOExceoption");
        }
    }

    public void process(String op, String plate){
        if(op.equals("A")){
            if(top < 10){
                System.out.println( plate + " Entered successfully.");
                push(plate);
                top++;

            }else{
                System.out.println("Arrival is full. There's any place to stop for "  + plate + " .");
            }
        }else{
            System.out.println("Looking for " + plate + " numbered car." );
            if(op.equals("D")){
                if(top < 10){
                    System.out.println(plate + " car is located in garage.");

                    top++;
                }else{
                    while(!empty()){
                        System.out.println(plate + " car moved out.");
                        park.push(pop());
                    }
                }
            }   
        }
    }
    public void output(String text){
        System.out.println(text);
    }


        public static void main(String args[]){
            CarParkingNeg caring = new CarParkingNeg();
            caring.park = new ArrayStack();
        caring.input();

        
        
    }
}