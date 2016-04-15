/**
 * *brief    Common Log
 *
 * *author    jp.ki@lge.com
 * *date    2014.08.18
 *
 * *version    v1.00    2014.08.18    jp.ki@lge.com    created
 */

package com.jp.ce.common;

import com.jp.ce.output.OutputManager;

public class CLog
{
    public static String sTAG = "JP";

    public static boolean sIsEnabled = false;

//    private static SimpleDateFormat sFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US);
    public static void printAppInfo() {
    	if (sIsEnabled) {
    		i(sTAG, "==========================================================");
//    		i(sTAG, "Version: " + packageInfo.versionName + " / " + packageInfo.versionCode);
//    		i(sTAG, "SharedUserId: " + packageInfo.sharedUserId);
//    		i(sTAG, "UpdateTime: " + sFormatter.format(new Date(packageInfo.firstInstallTime))
//    		+ " / " + sFormatter.format(new Date(packageInfo.lastUpdateTime)));
    		i(sTAG, "==========================================================");
    	}
    }

    public static void v(String className, String message)
    {
        if (!sIsEnabled || !CConfig.Log.verbose) {
            return;
        }

		System.out.println("[V][" + sTAG + "][" + className + "] " + message);
    }

    public static void d(String className, String message)
    {
        if (!sIsEnabled || !CConfig.Log.debug) {
            return;
        }

        System.out.println("[D][" + sTAG + "][" + className + "] " + message);
    }

    public static void i(String className, String message)
    {
        if (!sIsEnabled || !CConfig.Log.info) {
            return;
        }

        System.out.println("[I][" + sTAG + "][" + className + "] " + message);
    }

    public static void w(String className, String message)
    {
        if (!sIsEnabled || !CConfig.Log.warn) {
            return;
        }

        System.out.println("[W][" + sTAG + "][" + className + "] " + message);
    }

    public static void e(String className, String message)
    {
        if (!sIsEnabled || !CConfig.Log.error) {
            return;
        }

        System.out.println("[E][" + sTAG + "][" + className + "] " + message);
    }
    
    public static void print(String message)
    {
        System.out.println(message);
        OutputManager.getInstance().write(message);
    }

    public static void exception(String className, Throwable th) {
        if (!sIsEnabled || !CConfig.Log.error) {
            return;
        }

        StackTraceElement[] stacks = th.getStackTrace();
        if (!CConfig.Log.stack) {
        	if (stacks != null && stacks.length > 1) {
        		System.out.println("[E][" + sTAG + "][" + className + "] " + th.getStackTrace()[1] + ":" + th);
        	} else {
        		System.out.println("[E][" + sTAG + "][" + className + "] " + th);
        	}
        } else {
    		System.out.println("[E][" + sTAG + "][" + className + "] " + th);
        	if (stacks != null) {
        		for (StackTraceElement stack : stacks) {
            		System.out.println("[E][" + sTAG + "][" + className + "] " + stack);
        		}
        	}
        }
    }
    
    public static void exceptionStack(Throwable th) {
        if (!sIsEnabled || !CConfig.Log.error) {
            return;
        }

        StackTraceElement[] stacks = th.getStackTrace();
        System.out.println("[E][" + sTAG + "]" + th.toString());
    	if (stacks != null) {
    		for (StackTraceElement stack : stacks) {
    			System.out.println("[E][" + sTAG + "]" + stack.toString());
    		}
    	}
    }

    private static long sTestTimeCheck = -1;
    private static long sTestCount = 0;
    public static void test(boolean reset, String className, String message)
    {
    	if (!sIsEnabled) {
            return;
        }
    	
        if (sTestTimeCheck < 0 || reset) {
            sTestTimeCheck = System.currentTimeMillis();
            sTestCount = 0;
        }
        sTestCount++;

        System.out.println("[E][" + sTAG + "][" + className + "][" + "[" + sTestCount + "][" + (System.currentTimeMillis() - sTestTimeCheck) + "]" + message);

        sTestTimeCheck = System.currentTimeMillis();
    }

    public static void stack(String className, String message) {
    	if (!sIsEnabled) {
            return;
        }
    	
    	System.out.println("[E][" + sTAG + "][" + className + "] " + message);
        
        (new Throwable()).printStackTrace();
    }
}
