package com.example.mapper;

public class Point {
	
	public float x;
	public float y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float distanceEuclidean(Point p) {
		return (float) Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}
	
	public Point middlePoint(Point p) {
		return new Point(((p.x - x) / 2) + x, ((p.y - y) / 2) + y);
	}
}