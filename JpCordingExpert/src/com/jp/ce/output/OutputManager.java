/**
 * *brief
 *
 * *author	jp.ki@lge.com
 * *date	2016.04.02
 *
 * *version    v1.00    2014.04.02    jp.ki@lge.com    created
 */

package com.jp.ce.output;

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
}
