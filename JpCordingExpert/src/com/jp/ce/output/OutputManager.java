/**
 * *brief
 *
 * *author	jp.ki@lge.com
 * *date	2016.04.02
 *
 * *version    v1.00    2014.04.02    jp.ki@lge.com    created
 */

package com.jp.ce.output;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

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
    
    public String load(String path) {
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
    	
    	return mPath;
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
    
    public void compareOutput(String a, String b) {
    	CLog.d(TAG, "compareOutput a: " + a + ", b: " + b);
    	
    	InputStreamReader inputStreamReaderA = null;
    	BufferedReader bufferedReaderA = null;
    	
    	InputStreamReader inputStreamReaderB = null;
    	BufferedReader bufferedReaderB = null;
    	
        try {
        	inputStreamReaderA = new InputStreamReader(new FileInputStream(a));
        	bufferedReaderA = new BufferedReader(inputStreamReaderA);
        	
        	inputStreamReaderB = new InputStreamReader(new FileInputStream(b));
        	bufferedReaderB = new BufferedReader(inputStreamReaderB);
        	
        	long missCount = 0;
        	long line = 0;
        	String dataA = null;
        	String dataB = null;
        	while ((dataA = bufferedReaderA.readLine()) != null) {
        		dataB = bufferedReaderB.readLine();
        		
        		if (!dataA.equals(dataB)) {
        			CLog.d(TAG, "mismatch line: " + line + ", a: " + dataA + ", b:" + dataB);
        			missCount++;
        		}
        		
        		line++;
        	}
        	
        	CLog.d(TAG, "compareOutput missCount: " + missCount + ", line: " + line);
    	} catch (Exception e) {
    		CLog.exception(TAG, e);
    	} finally {
    		if (bufferedReaderA != null) {
    			try {
    				bufferedReaderA.close();
    			} catch (Exception e) {
    				;
    			}
    			bufferedReaderA = null;
    		}
    		
    		if (inputStreamReaderA != null) {
    			try {
    				inputStreamReaderA.close();
    			} catch (Exception e) {
    				;
    			}
    			inputStreamReaderA = null;
    		}
    		
    		if (bufferedReaderB != null) {
    			try {
    				bufferedReaderB.close();
    			} catch (Exception e) {
    				;
    			}
    			bufferedReaderB = null;
    		}
    		
    		if (inputStreamReaderB != null) {
    			try {
    				inputStreamReaderB.close();
    			} catch (Exception e) {
    				;
    			}
    			inputStreamReaderB = null;
    		}
    	}
    }
}
