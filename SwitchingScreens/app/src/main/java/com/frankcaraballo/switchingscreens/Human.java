package com.frankcaraballo.switchingscreens;

import java.io.Serializable;

/**
 * Created by frank on 01/16/15.
 */
public class Human implements Serializable {
    private double heigth, weigth;
    private String name;

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Human(double heigth, double weigth, String name) {
        this.heigth = heigth;
        this.weigth = weigth;
        this.name = name;

    }
}
