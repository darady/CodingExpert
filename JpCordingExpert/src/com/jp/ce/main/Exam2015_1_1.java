package com.jp.ce.main;


import java.util.Hashtable;

import com.jp.ce.common.CLog;
import com.jp.ce.input.InputManager;
import com.jp.ce.output.OutputManager;

public class Exam2015_1_1 {
	public static final String TAG = Exam2015_1_1.class.getSimpleName();
	
	private static final String DATA = "./data/problem1.in.short"; //problem1.in.short";

	private static InputManager input = InputManager.getInstance();
	private static OutputManager output = OutputManager.getInstance();
	
	private static int DIS_0 = 0;
	private static int DIS_NEAR = 1;
	private static int DIS_FAR = 2;
	
	private static int DIR_0 = 0;
	private static int DIR_1 = 1;
	private static int DIR_2 = 2;
	private static int DIR_3 = 3;
	private static int DIR_4 = 4;
	private static int DIR_5 = 5;
	private static int DIR_6 = 6;
	private static int DIR_7 = 7;
	private static int DIR_8 = 8;
	private static int DIR_FAIL = 9;
	
	/*************************************************************************************/
    // base method
    /*************************************************************************************/
	public static void start() {
		initData();
		
		long time = System.currentTimeMillis();
		
		input.load(DATA);
		output.load(DATA);
		
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
		
		CLog.print("delay: " + (System.currentTimeMillis() - time));
		
		input.release();
		output.release();
	}
	
	/*************************************************************************************/
    // algorithm
    /*************************************************************************************/
	private static int beforeDirection = DIR_FAIL;
	
	private static int doMeasure(String data) {
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
	
	private static int getScore(int a, int b) {
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
	
	private static Hashtable<Integer, Integer> sDistanceScoreTable = new Hashtable<Integer, Integer>();
	private static void initData() {
		sDistanceScoreTable.put(11, 0);
		sDistanceScoreTable.put(12, 13);
		sDistanceScoreTable.put(13, 29);
		sDistanceScoreTable.put(14, 15);
		sDistanceScoreTable.put(15, 14);
		sDistanceScoreTable.put(16, 29);
		sDistanceScoreTable.put(17, 29);
		sDistanceScoreTable.put(18, 29);
		sDistanceScoreTable.put(19, 29);
		sDistanceScoreTable.put(10, 29);
		
		sDistanceScoreTable.put(21, 17);
		sDistanceScoreTable.put(22, 0);
		sDistanceScoreTable.put(23, 13);
		sDistanceScoreTable.put(24, 16);
		sDistanceScoreTable.put(25, 15);
		sDistanceScoreTable.put(26, 14);
		sDistanceScoreTable.put(27, 29);
		sDistanceScoreTable.put(28, 29);
		sDistanceScoreTable.put(29, 29);
		sDistanceScoreTable.put(20, 29);
		
		sDistanceScoreTable.put(31, 29);
		sDistanceScoreTable.put(32, 17);
		sDistanceScoreTable.put(33, 0);
		sDistanceScoreTable.put(34, 29);
		sDistanceScoreTable.put(35, 16);
		sDistanceScoreTable.put(36, 15);
		sDistanceScoreTable.put(37, 29);
		sDistanceScoreTable.put(38, 29);
		sDistanceScoreTable.put(39, 29);
		sDistanceScoreTable.put(30, 29);
		
		sDistanceScoreTable.put(41, 11);
		sDistanceScoreTable.put(42, 12);
		sDistanceScoreTable.put(43, 29);
		sDistanceScoreTable.put(44, 0);
		sDistanceScoreTable.put(45, 13);
		sDistanceScoreTable.put(46, 29);
		sDistanceScoreTable.put(47, 15);
		sDistanceScoreTable.put(48, 14);
		sDistanceScoreTable.put(49, 29);
		sDistanceScoreTable.put(40, 29);
		
		sDistanceScoreTable.put(51, 18);
		sDistanceScoreTable.put(52, 11);
		sDistanceScoreTable.put(53, 12);
		sDistanceScoreTable.put(54, 17);
		sDistanceScoreTable.put(55, 0);
		sDistanceScoreTable.put(56, 13);
		sDistanceScoreTable.put(57, 16);
		sDistanceScoreTable.put(58, 15);
		sDistanceScoreTable.put(59, 14);
		sDistanceScoreTable.put(50, 29);
		
		sDistanceScoreTable.put(61, 29);
		sDistanceScoreTable.put(62, 18);
		sDistanceScoreTable.put(63, 11);
		sDistanceScoreTable.put(64, 29);
		sDistanceScoreTable.put(65, 17);
		sDistanceScoreTable.put(66, 0);
		sDistanceScoreTable.put(67, 29);
		sDistanceScoreTable.put(68, 16);
		sDistanceScoreTable.put(69, 15);
		sDistanceScoreTable.put(60, 29);
		
		sDistanceScoreTable.put(71, 29);
		sDistanceScoreTable.put(72, 29);
		sDistanceScoreTable.put(73, 29);
		sDistanceScoreTable.put(74, 11);
		sDistanceScoreTable.put(75, 12);
		sDistanceScoreTable.put(76, 29);
		sDistanceScoreTable.put(77, 0);
		sDistanceScoreTable.put(78, 13);
		sDistanceScoreTable.put(79, 29);
		sDistanceScoreTable.put(70, 14);
		
		sDistanceScoreTable.put(81, 29);
		sDistanceScoreTable.put(82, 29);
		sDistanceScoreTable.put(83, 29);
		sDistanceScoreTable.put(84, 18);
		sDistanceScoreTable.put(85, 11);
		sDistanceScoreTable.put(86, 12);
		sDistanceScoreTable.put(87, 17);
		sDistanceScoreTable.put(88, 0);
		sDistanceScoreTable.put(89, 13);
		sDistanceScoreTable.put(80, 15);
		
		sDistanceScoreTable.put(91, 29);
		sDistanceScoreTable.put(92, 29);
		sDistanceScoreTable.put(93, 29);
		sDistanceScoreTable.put(94, 29);
		sDistanceScoreTable.put(95, 18);
		sDistanceScoreTable.put(96, 11);
		sDistanceScoreTable.put(97, 29);
		sDistanceScoreTable.put(98, 17);
		sDistanceScoreTable.put(99, 0);
		sDistanceScoreTable.put(90, 16);
		
		sDistanceScoreTable.put(1, 29);
		sDistanceScoreTable.put(2, 29);
		sDistanceScoreTable.put(3, 29);
		sDistanceScoreTable.put(4, 29);
		sDistanceScoreTable.put(5, 29);
		sDistanceScoreTable.put(6, 29);
		sDistanceScoreTable.put(7, 18);
		sDistanceScoreTable.put(8, 11);
		sDistanceScoreTable.put(9, 12);
		sDistanceScoreTable.put(0, 0);
	}
}
