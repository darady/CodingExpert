package com.jp.ce.main;

public abstract class ExamBase {
	public static final String TAG = ExamBase.class.getSimpleName();
	
	public abstract String getDataPath();
	public abstract void start();
	
	public abstract String getSolvedPath();
}
