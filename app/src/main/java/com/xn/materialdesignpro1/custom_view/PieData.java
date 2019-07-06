package com.xn.materialdesignpro1.custom_view;

/**
 * 饼状图各部分数据
 */
public class PieData {
    private String name;
    private float percent;
    private float value;

    private int color;
    private float angle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public PieData(String name, float value) {
        this.name = name;
        this.value = value;
    }
}
