/**
 * *brief    Common Config
 *
 * *author    jp.ki@lge.com
 * *date    2014.08.18
 *
 * *version    v1.00    2014.08.18    jp.ki@lge.com    created
 */

package com.jp.ce.common;

public class CConfig
{
    public static class Feature
    {
    	public static final boolean FEATURE_SUPPORT_XXX = false;
    }

    /*************************************************************************************/
    // define: debugging
    /*************************************************************************************/
    public static class Log
    {
        public static final boolean verbose = true;
        public static final boolean debug = true;
        public static final boolean info = true;
        public static final boolean warn = true;
        public static final boolean error = true;
        
        public static final boolean stack = true;
    }
}
