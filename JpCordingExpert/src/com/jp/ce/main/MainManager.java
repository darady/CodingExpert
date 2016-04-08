/**
 * *brief
 *
 * *author	jp.ki@lge.com
 * *date	2016.04.02
 *
 * *version    v1.00    2014.04.02    jp.ki@lge.com    created
 */

package com.jp.ce.main;

import com.jp.ce.common.CLog;
import com.jp.ce.input.InputManager;
import com.jp.ce.output.OutputManager;

public class MainManager {
    public static final String TAG = MainManager.class.getSimpleName();

//    private static final int SCAN_INTERVAL = 10 * 1000;

    /*************************************************************************************/
    // sub class
    /*************************************************************************************/

    /*************************************************************************************/
    // listener
    /*************************************************************************************/

    /*************************************************************************************/
    // local
    /*************************************************************************************/

    /*************************************************************************************/
    // singleton
    /*************************************************************************************/
    private static MainManager sInstance = new MainManager();
    private MainManager() {
    };
    public static MainManager getInstance() {
        return sInstance;
    }
    
    /*************************************************************************************/
    // main
    /*************************************************************************************/
    public static void main(String args[]) {
    	initailize();
    	
    	doExam();
    	
    	terminate();
    }
    
    private static void doExam() {
		long time = System.currentTimeMillis();
		
		ExamBase target = new Exam2015_1_1();
		
		InputManager.getInstance().load(target.getDataPath());
		OutputManager.getInstance().load(target.getDataPath());
		
    	target.start();
    	
    	InputManager.getInstance().release();
    	OutputManager.getInstance().release();
    	
		CLog.i(TAG, "delay: " + (System.currentTimeMillis() - time));
    }

    /*************************************************************************************/
    // base method
    /*************************************************************************************/
    public static void initailize() {
    	CLog.d(TAG, "initailize");
    	
    	InputManager.getInstance().initailize();
    	OutputManager.getInstance().initailize();
    }
    
    public static void terminate() {
    	CLog.d(TAG, "terminate");
    	
    	OutputManager.getInstance().terminate();
    	InputManager.getInstance().terminate();
    }
}
