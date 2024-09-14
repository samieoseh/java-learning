package com.example.calculator;

public class AdvCalc extends Calc {
    public int multi(int n1, int n2) {
        return n1 * n2;
    }

    public int div(int n1, int n2) {
        return n1 / n2;
    }
    
    // cannot override the final keyword show 
    // public void show() {
    //     System.out.println("In AdvCalc show");
    // }
}
