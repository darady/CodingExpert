package com.jp.ce.main;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

import com.jp.ce.common.CLog;
import com.jp.ce.input.InputManager;

public class Exam2015_1_3 extends ExamBase {
	public static final String TAG = Exam2015_1_3.class.getSimpleName();
	
	public static final String DATA = "./data/problem2/problem3/in_small.txt"; //problem1.in.short";
	public static final String SOLVED = "./data/problem2/problem3/out_small.txt"; //problem2/in_small.txt"; //problem1.in.short";

	private static InputManager input = InputManager.getInstance();
	
	/*************************************************************************************/
    // base method
    /*************************************************************************************/
	@Override
	public String getDataPath() {
		return DATA;
	}
	
	@Override
	public String getSolvedPath() {
		return SOLVED;
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
			
			int result = doMeasure();
			
			CLog.d(TAG, "result set: " + setCount + ", result: " + result);
			
			CLog.print(result + "");
		}
	}
	
	/*************************************************************************************/
    // algorithm
    /*************************************************************************************/
	private int doMeasure() {
		CLog.d(TAG, "doMeasure");
		
		String data = input.readLine();
		String[] NM = data.split(" ");
		
		int n = Integer.parseInt(NM[0]);
		int m = Integer.parseInt(NM[1]);
		
		initData(n);
		
		CLog.d(TAG, "doMeasure n: " + n + ", m: " + m);
		
		for (int i = 0; i < m; i++) {
			data = input.readLine();
			String[] AB = data.split(" ");
			
			int a = Integer.parseInt(AB[0]);
			int b = Integer.parseInt(AB[1]);
			
			mPairs.add(new Pair(a, b));
		}
		
		mMinScore = getScore(orignalList);
		
		CLog.d(TAG, "doMeasure ori: " + mMinScore);
		
		makeGoodScore(orignalList, new LinkedList<Integer>());
		
		return mMinScore;
	}
	
	private int mMinScore = 0;
	private void makeGoodScore(LinkedList<Integer> ori, LinkedList<Integer> dest) {
		if (ori.isEmpty()) {
			int score = getScore(dest);
			if (score < mMinScore) {
				mMinScore = score;
			}	
			return;
		}
		
//		int score = getScore(dest);
//		if (score >= mMinScore) {
//			return;
//		}
		
		if (ori.size() > 1) {
			makeGoodScore(newRemovedList(ori, true), newAddedList(dest, ori.getFirst()));
			makeGoodScore(newRemovedList(ori, false), newAddedList(dest, ori.getLast()));
		} else {
			makeGoodScore(newRemovedList(ori, true), newAddedList(dest, ori.getFirst()));
		}
	}
	
	private LinkedList<Integer> newRemovedList(LinkedList<Integer> data, boolean isFirst) {
		LinkedList<Integer> result = (LinkedList<Integer>)data.clone();
		if (isFirst) {
			result.removeFirst();
		} else {
			result.removeLast();
		}
		
		return result;
	}
	
	private LinkedList<Integer> newAddedList(LinkedList<Integer> data, int value) {
		LinkedList<Integer> result = (LinkedList<Integer>)data.clone();
		result.add(value);
		
		return result;
	}
	
	
	
	/*************************************************************************************/
    // data
    /*************************************************************************************/
	LinkedList<Integer> orignalList = new LinkedList<Integer>();
	Hashtable<String, Integer> solved = new Hashtable<String, Integer>();
	
	public void initData(int n) {
		orignalList.clear();
		for (int i = 1; i < n + 1; i++) {
			orignalList.add(i);
		}
		mPairs.clear();
	}
	
	public int getScore(LinkedList<Integer> list) {
		if (list.size() < 2) {
			return 0;
		}
		
//		String key = makeKey(list);
//		
//		Integer data = solved.get(key);
//		if (data != null) {
//			return data;
//		}
//		
//		int result = 0;
//		Integer dataBefore = solved.get(makeBeforeKey(list));
//		if (dataBefore != null) {
//			int last = list.getLast();
//
//			for (Pair p : mPairs) {
//				if (p.a == last || p.b == last) {
//					int indexA = list.indexOf(p.a);
//					int indexB = list.indexOf(p.b);
//
//					if (indexA >= 0 && indexB >= 0) {
//						result += Math.abs(indexA - indexB);
//					}
//				}
//			}
//
//			solved.put(key, result);
//			solved.put(makeReverseKey(list), result);
//
//			return result;
//		}
		
		int result = 0;
		for (Pair p : mPairs) {
			int indexA = list.indexOf(p.a);
			int indexB = list.indexOf(p.b);
			
			if (indexA >= 0 && indexB >= 0) {
				result += Math.abs(indexA - indexB);
			}
		}
		
//		solved.put(key, result);
//		solved.put(makeReverseKey(list), result);

		return result;
	}

	public static String makeKey(LinkedList<Integer> list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				sb.append(list.get(i));
			} else {
				sb.append(list.get(i) + ",");
			}
		}
		return sb.toString();
	}
	
	public static String makeBeforeKey(LinkedList<Integer> list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size() - 1; i++) {
			if (i == list.size() - 2) {
				sb.append(list.get(i));
			} else {
				sb.append(list.get(i) + ",");
			}
		}
		return sb.toString();
	}
	
	public static String makeReverseKey(LinkedList<Integer> list) {
		StringBuffer sb = new StringBuffer();
		for (int i = list.size() - 1; i > 0; i--) {
			if (i == 0) {
				sb.append(list.get(i));
			} else {
				sb.append(list.get(i) + ",");
			}
		}
		return sb.toString();
	}

	ArrayList<Pair> mPairs = new ArrayList<Pair>();
	public class Pair {
		public int a;
		public int b;
		
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

//	class XComparator implements Comparator<Point> { 
//		public int compare(Point o1, Point o2) {
//			return o1.y > o2.y ? 1 : (o1.y == o2.y ? 0 : -1);
//		}
//	}
	
//	public class Wall {
//		public long minX = 0;
//		public long maxX = 0;
//		
//		public long minY = 0;
//		public long maxY = 0;
//		
//		public long gap = 0;
//		public long exception = 0;
//		
//		public boolean checkValid(Point p) {
//			if (p.x >= minX && p.x <= maxX
//					&& p.y >= minY && p.y <= maxY) {
//				if (p.x > minX + gap && p.x < maxX - gap
//					&& p.y > minY + gap && p.y < maxY - gap) {
//					return false;
//				}
//				
//				return true;
//			}
//			
//			return false;
//		}
//	}
}
