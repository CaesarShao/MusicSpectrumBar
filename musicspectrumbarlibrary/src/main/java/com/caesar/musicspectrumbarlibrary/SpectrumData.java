package com.caesar.musicspectrumbarlibrary;

/**
 * created by Caesar on 2019/7/4
 * email : 15757855271@163.com
 */
public class SpectrumData {
    private float left;
    private float right;
    private float top;
    private float bottom;
    private String color;

    public SpectrumData(float left, float right, float top, float bottom, String color) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.color = color;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
