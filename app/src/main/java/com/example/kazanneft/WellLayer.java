package com.example.kazanneft;

public class WellLayer {
    private final String backgroundColor;
    private String rockTypeName;
    private String startPoint;
    private String endPoint;

    public WellLayer(String rockTypeName, String startPoint, String endPoint, String backgroundColor) {
        this.rockTypeName = rockTypeName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.backgroundColor = backgroundColor;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setRockTypeName(String rockTypeName) {
        this.rockTypeName = rockTypeName;
    }

    public String getRockTypeName() {
        return rockTypeName;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    @Override
    public String toString() {
        return "WellLayer{" +
                "backgroundColor='" + backgroundColor + '\'' +
                ", rockTypeName='" + rockTypeName + '\'' +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                '}';
    }
}
