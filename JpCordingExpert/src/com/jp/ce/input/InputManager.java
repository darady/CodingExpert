/**
 * *brief
 *
 * *author	jp.ki@lge.com
 * *date	2016.04.02
 *
 * *version    v1.00    2014.04.02    jp.ki@lge.com    created
 */

package com.jp.ce.input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.jp.ce.common.CFile;
import com.jp.ce.common.CLog;

public class InputManager {
    public static final String TAG = InputManager.class.getSimpleName();

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
    
//    private ReaderRunnable mReaderRunnable = null;
//    private IReaderRunnable mIReaderRunnable = null;
    private InputStreamReader mInputStreamReader = null;
	private BufferedReader mBufferedReader = null;
    
    /*************************************************************************************/
    // singleton
    /*************************************************************************************/
    private static InputManager sInstance = new InputManager();
    private InputManager() {
    };
    public static InputManager getInstance() {
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
    	
    	mPath = path;
    	
    	try {
    		mInputStreamReader = new InputStreamReader(new FileInputStream(mPath));
    		mBufferedReader = new BufferedReader(mInputStreamReader);
    	} catch (Exception e) {
    		CLog.exception(TAG, e);
    	}
    }
    
    public void release() {
    	mPath = null;
    	
    	if (mBufferedReader != null) {
			try {
				mBufferedReader.close();
			} catch (Exception e) {
				;
			}
			mBufferedReader = null;
		}
		
		if (mInputStreamReader != null) {
			try {
				mInputStreamReader.close();
			} catch (Exception e) {
				;
			}
			mInputStreamReader = null;
		}
    }
    
    public String readLine() {
//    	CLog.d(TAG, "terminate");
    	
    	if (mBufferedReader != null) {
    		try {
				return mBufferedReader.readLine();
			} catch (IOException e) {
				CLog.exception(TAG, e);
			}
    	}
    	
    	return null;
    }
    
//    /*************************************************************************************/
//    // base method
//    /*************************************************************************************/
//    public static interface IReaderRunnable {
//		public void onUpdateState(int percent);
//		public void onComplete(boolean result);
//	}
//    
//    private static class ReaderRunnable implements Runnable {
//		private String mPath = null;
//		private IReaderRunnable mListener = null;
//		private boolean mIsExit = false;
//		
//		public ReaderRunnable(String path, IReaderRunnable listener) {
//			mPath = path;
//			mListener = listener;
//		}
//		
//		@Override
//		public void run() {
//            CLog.d(TAG, "ReaderRunnable path: " + mPath + ", listener: " + mListener);
//            
//			long time = System.currentTimeMillis();
//			
//			boolean result = false;
//			
//			InputStreamReader inputStreamReader = null;
//			BufferedReader bufferedReader = null;
//			
//			try {
//				long totalSize = 0;
//				if ((totalSize = CFile.size(mPath)) <= 0) {
//					CLog.e(TAG, "ReaderRunnable not exist fail: " + mPath);
//					return;
//				}
//				
//				inputStreamReader = new InputStreamReader(new FileInputStream(mPath));
//			    bufferedReader = new BufferedReader(inputStreamReader); 
//			    
//			    Thread.currentThread().wait();
//			    
////			    bufferedReader.readLine()
//			    
//				//TOD read line
//			} catch (Exception e) {
//				CLog.exception(TAG, e);
//			} finally {
//				if (bufferedReader != null) {
//					try {
//						bufferedReader.close();
//					} catch (Exception e) {
//						;
//					}
//					bufferedReader = null;
//				}
//				
//				if (inputStreamReader != null) {
//					try {
//						inputStreamReader.close();
//					} catch (Exception e) {
//						;
//					}
//					inputStreamReader = null;
//				}
//				
//				mListener.onComplete(result);
//			}
//			
//			CLog.d(TAG, "ReaderRunnable path: " + mPath + ", finish delay: " + (System.currentTimeMillis() - time));
//		}
//		
//		public void exit() {
//			mIsExit = true;
//			Thread.interrupted();
//		}
//		
//		public String readLine() {
//			return null;
//		}
//	}
    
}
