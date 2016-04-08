package com.jp.ce.main;


import java.util.Hashtable;

import com.jp.ce.common.CLog;
import com.jp.ce.input.InputManager;

public class Exam2015_1_2 extends ExamBase {
	public static final String TAG = Exam2015_1_2.class.getSimpleName();
	
	public static final String DATA = "./data/problem2.in.short"; //problem1.in.short";

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
		initData();
		
		int total = 0;
		String data = input.readLine();
		if (data != null) {
			total = Integer.parseInt(data);
		}
		
		CLog.d(TAG, "start total: " + total);
			
		int itemCount = 0;
		int minScore = Integer.MAX_VALUE;
		String minNumber = null;
		
		int setCount = 0;
		
		while ((data = input.readLine()) != null) {
			itemCount = Integer.parseInt(data);
			
			setCount++;
			
			CLog.d(TAG, "set start: " + setCount + ", itemCount: " + itemCount);
			
			for (int i = 0; i < itemCount ; i++) {
				data = input.readLine();
				
				int score = doMeasure(data);
				if (score < minScore) {
					minScore = score;
					minNumber = data;
					
					CLog.d(TAG, "find set: " + setCount + ", minScore: " + minScore
							 + ", minNumber: " + minNumber);
				}
			}
			
			CLog.d(TAG, "result set: " + setCount + ", minScore: " + minScore
					 + ", minNumber: " + minNumber);
			
			CLog.print(minNumber);
			
			minScore = Integer.MAX_VALUE;
			minNumber = null;
		}
	}
	
	/*************************************************************************************/
    // algorithm
    /*************************************************************************************/
	private int beforeDirection = DIR_FAIL;
	
	private int doMeasure(String data) {
		CLog.d(TAG, "doMeasure data: " + data);
		
		int result = 0;
		int index = 0;
		
		int a = Character.getNumericValue(data.charAt(index++));
		int b = Character.getNumericValue(data.charAt(index++));
		
		CLog.d(TAG, "doMeasure a: " + a + ", b: " + b);
		
		result += sDistanceScoreTable.get((a * 10 + b)) / 10;
		
		for (int i = index; i < data.length(); i++) {
			
			a = b;
			b = Character.getNumericValue(data.charAt(i));
			
			result += getScore(a, b);
		}
		
		return result;
	}
	
	private int getScore(int a, int b) {
		CLog.d(TAG, "getScore a: " + a + ", b: " + b);
		
		int value = sDistanceScoreTable.get((a * 10 + b));
		
		int score = value / 10;
		int direction = value % 10;
		
		int result = 0;
		
		if (score == 0) {
			result = 0;
		} else if (score == 1) {
			if (beforeDirection == direction) {
				result = 1;
			} else {
				result = 2;
			}
		} else {
			result = 3;
		}
		
		beforeDirection = direction;
		
		return result;
	}
	
	/*************************************************************************************/
    // data
    /*************************************************************************************/
	public class Point {
		public double x;
		public double y;
	}
	
	public class Wall {
		public Point minPoint = new Point();
		public Point maxPoint = new Point();
		
		public double gap = 0;
		
		public boolean checkValid(Point point) {
			return false;
		}
	}
	
	
	
	
	private Hashtable<Integer, Integer> sDistanceScoreTable = new Hashtable<Integer, Integer>();
	private void initData() {
		

	}
}
