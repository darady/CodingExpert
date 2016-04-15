package com.jp.ce.main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.jp.ce.common.CLog;
import com.jp.ce.input.InputManager;

public class Exam2015_1_2 extends ExamBase {
	public static final String TAG = Exam2015_1_2.class.getSimpleName();
	
	public static final String DATA = "./data/problem2.in"; //problem1.in.short";

	private static InputManager input = InputManager.getInstance();
	
	/*************************************************************************************/
    // base method
    /*************************************************************************************/
	@Override
	public String getDataPath() {
		return DATA;
	}

	@Override
	public void start() {
		int total = 0;
		String data = input.readLine();
		if (data != null) {
			total = Integer.parseInt(data);
		}
		
		CLog.d(TAG, "start total: " + total);
			
		int setCount = 0;
		
		for (int i = 0; i < total; i++) {
			setCount++;
			
			CLog.d(TAG, "set start: " + setCount);
			
			boolean result = doMeasure();
			
			CLog.d(TAG, "result set: " + setCount + ", result: " + result);
			
			CLog.print(result ? "YES" : "NO");
		}
	}
	
	/*************************************************************************************/
    // algorithm
    /*************************************************************************************/
	private boolean doMeasure() {
		CLog.d(TAG, "doMeasure");
		
		mPoints.clear();
		
		String data = input.readLine();
		String[] NKW = data.split(" ");
		
		int n = Integer.parseInt(NKW[0]);
		int k = Integer.parseInt(NKW[1]);
		int w = Integer.parseInt(NKW[2]);
		
		CLog.d(TAG, "doMeasure n: " + n + ", k: " + k + ", w: " + w);
		
		long minX = Long.MAX_VALUE;
		long maxX = Long.MIN_VALUE;
		
		for (int i = 0; i < n; i++) {
			data = input.readLine();
			String[] XY = data.split(" ");
			
			long x = Long.parseLong(XY[0]);
			long y = Long.parseLong(XY[1]);
			
			mPoints.add(new Point(x, y));
			
			minX = Math.min(x, minX);
			maxX = Math.max(x, maxX);
		}
		
		if (Math.abs(minX - maxX) <= w * 2) {
			return true;
		}
		
		Collections.sort(mPoints, new XComparator());
		
		Wall wall = new Wall();
		wall.minX = minX;
		wall.maxX = maxX;
		wall.gap = w;
		
		CLog.d(TAG, "doMeasure minX: " + wall.minX + ", maxX: " + wall.maxX + ", gap: " + wall.gap);
		
		wall.minY = mPoints.get(k).y;
		wall.maxY = mPoints.get(mPoints.size() - 1).y;
		
		CLog.d(TAG, "doMeasure minY: " + wall.minY + ", maxY: " + wall.maxY);
		
		boolean result = true;
		for (int i = k; i < mPoints.size() - k; i++) {
			if (!wall.checkValid(mPoints.get(i))) {
				result = false;
				break;
			}
		}
		
		if (result) {
			return true;
		}
		
		wall.minY = mPoints.get(0).y;
		wall.maxY = mPoints.get(mPoints.size() - k - 1).y;
		
		CLog.d(TAG, "doMeasure minY: " + wall.minY + ", maxY: " + wall.maxY);
		
		result = true;
		for (int i = 0; i < mPoints.size() - k; i++) {
			if (!wall.checkValid(mPoints.get(i))) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	/*************************************************************************************/
    // data
    /*************************************************************************************/
	ArrayList<Point> mPoints = new ArrayList<Point>();
	public class Point {
		public long x;
		public long y;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}

	class XComparator implements Comparator<Point> { 
		public int compare(Point o1, Point o2) {
			return o1.y > o2.y ? 1 : (o1.y == o2.y ? 0 : -1);
		}
	}
	
	public class Wall {
		public long minX = 0;
		public long maxX = 0;
		
		public long minY = 0;
		public long maxY = 0;
		
		public long gap = 0;
		public long exception = 0;
		
		public boolean checkValid(Point p) {
			if (p.x >= minX && p.x <= maxX
					&& p.y >= minY && p.y <= maxY) {
				if (p.x > minX + gap && p.x < maxX - gap
					&& p.y > minY + gap && p.y < maxY - gap) {
					return false;
				}
				
				return true;
			}
			
			return false;
		}
	}
}
