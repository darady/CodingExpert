/**
 * *brief
 *
 * *author	jp.ki@lge.com
 * *date	2016.04.02
 *
 * *version    v1.00    2014.04.02    jp.ki@lge.com    created
 */

package com.jp.ce.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.jp.ce.common.CFile;
import com.jp.ce.common.CLog;

public class OutputManager {
    public static final String TAG = OutputManager.class.getSimpleName();

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
    private String mPath = null;
    
    private FileWriter mFileWriter = null;
    private BufferedWriter mBufferedWriter = null;
    
    /*************************************************************************************/
    // singleton
    /*************************************************************************************/
    private static OutputManager sInstance = new OutputManager();
    private OutputManager() {
    };
    public static OutputManager getInstance() {
        return sInstance;
    }
    
    /*************************************************************************************/
    // base method
    /*************************************************************************************/
    public void initailize() {
    	CLog.d(TAG, "initailize");
    	
    	
    }
    
    public void terminate() {
    	CLog.d(TAG, "terminate");
    	
    	
    }
    
    public void load(String path) {
    	CLog.d(TAG, "load path: " + path);
    	
    	release();
    	
    	mPath = path + ".output";
    	
    	try {
    		CFile.delete(mPath);
    		
    		mFileWriter = new FileWriter(mPath, false);
    		mBufferedWriter = new BufferedWriter(mFileWriter);
    	} catch (Exception e) {
    		CLog.exception(TAG, e);
    	}
    }
    
    public void release() {
    	mPath = null;
    	
    	if (mBufferedWriter != null) {
            try {
            	mBufferedWriter.flush();
            } catch (Exception e) {
                ;
            }
    		try {
    			mBufferedWriter.close();
    		} catch (Exception e) {
    			;
    		}
    		mBufferedWriter = null;
    	}

    	if (mFileWriter != null) {
    		try {
    			mFileWriter.close();
    		} catch (Exception e) {
    			;
    		}
    		mFileWriter = null;
    	}
    }
    
    public void write(String data) {
    	if (mBufferedWriter != null) {
    		try {
				mBufferedWriter.write(data);
				mBufferedWriter.newLine();
			} catch (IOException e) {
				CLog.exception(TAG, e);
			}
    	}
    }
}
