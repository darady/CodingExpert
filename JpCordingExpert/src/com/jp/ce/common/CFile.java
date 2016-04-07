/**
 * *brief    Common File
 *
 * *author    jp.ki@lge.com
 * *date    2014.08.18
 *
 * *version    v1.00    2014.08.18    jp.ki@lge.com    created
 */

package com.jp.ce.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CFile
{
    private static final String TAG = CFile.class.getSimpleName();

    public static String getFileExtension(String fileName)
    {
        int index = 0;

        if (fileName == null)
        {
            CLog.e(TAG, "invalid fileName");
            return null;
        }

        if ((index = fileName.lastIndexOf(".")) > -1) {
            String ext = fileName.substring(index + 1);
            if (ext.length() < 2 || ext.length() > 4) {
                return null;
            }

            return ext;
        }

        return null;
    }

    public static String[] splitFileNameFromFullPath(String fullpath)
    {
        int index = 0;

        if (fullpath == null)
        {
            CLog.e(TAG, "invalid fullpath");
            return null;
        }

        if ((index = fullpath.lastIndexOf("/")) > -1) {
            String[] result = new String[2];
            result[0] = fullpath.substring(0, index);
            result[1] = fullpath.substring(index + 1);

            return result;
        }

        return null;
    }

    public static String getFileNameFromFullPath(String fullpath)
    {
        int index = 0;

        if (fullpath == null)
        {
            CLog.e(TAG, "invalid fullpath");
            return null;
        }

        if ((index = fullpath.lastIndexOf("/")) > -1) {
            String result = fullpath.substring(index + 1);
            return result;
        }

        return null;
    }

    public static String[] splitFileExtension(String fileName)
    {
        int index = 0;

        if (fileName == null)
        {
            CLog.e(TAG, "invalid fileName");
            return null;
        }

        String[] result = new String[2];
        if ((index = fileName.lastIndexOf(".")) > -1) {
            result[0] = fileName.substring(0, index);
            result[1] = fileName.substring(index + 1);

            return result;
        }

        result[0] = fileName;
        result[1] = "";

        return result;
    }

    public static boolean exist(String fileName)
    {
        if (fileName == null) {
            return false;
        }

        File file = new File(fileName);
        return (file != null) ? file.exists() : false;
    }

    public static long size(String fileName)
    {
        if (fileName == null) {
            return -1L;
        }

        File file = new File(fileName);

        if (file == null || file.exists() == false) {
            return -2L;
        }

        return file.length();
    }

    public static boolean delete(String fileName)
    {
        if (fileName == null) {
            return false;
        }

        File file = new File(fileName);

        if (file.exists()) {
            file.delete();
        }

        return true;
    }

    public static boolean copy(String src, String dst)
    {
        if (src == null || dst == null) {
            return false;
        }

        boolean result = false;
        InputStream in = null;
        OutputStream out = null;
        try {
            File f1 = new File(src);
            File f2 = new File(dst);
            in = new FileInputStream(f1);
              out = new FileOutputStream(f2);

              byte[] buf = new byte[1024];
              int len;
              while ((len = in.read(buf)) > 0) {
                  out.write(buf, 0, len);
              }
              out.flush();

              result = true;
        }
        catch (Exception e)
        {
        	CLog.exception(TAG, e);
        } finally {
            try {
                 if (in != null) {
                     in.close();
                 }
            } catch (Exception e) {
            	CLog.exception(TAG, e);
            }
            in = null;

            try {
                 if (out != null) {
                     out.close();
                 }
            } catch (Exception e) {
            	CLog.exception(TAG, e);
            }
            out = null;
        }

        return result;
    }

    public static String checkFileName(String name)
    {
        String []filter_word = {"\\?", "\\/", "\\*", "\\|", "\\\\", "\\\"", "\\:", "\\<", "\\>"};

        if (name == null) {
            return null;
        }

        String result = name;
        try {
            for (int i = 0; i < filter_word.length; i++) {
                result = result.replaceAll(filter_word[i], "");
            }

            if (result.length() > 100) {
                result = result.substring(0, 50) + "..." + result.substring(result.length() - 50, result.length());
            }

            return result;
        } catch (Exception e) {
        	CLog.exception(TAG, e);
        }

        return null;
    }

    public static boolean makeDir(String path)
    {
        if (path == null) {
            return false;
        }

        try {
            File file = new File(path);
            if (file != null) {
                return file.mkdir();
            }
        } catch (Exception e) {
        	CLog.exception(TAG, e);
        }

        return false;
    }

    public static boolean write(String fileName, ByteArrayOutputStream byteArray) {
        if (fileName == null || byteArray == null) {
            return false;
        }

        File file = new File(fileName);

        if (file.isDirectory()) {
            return false;
        }

        File parent = file.getParentFile();
        if (parent == null || (parent.exists() == false && parent.mkdir() == false)) {
            return false;
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            fos.write(byteArray.toByteArray());

            return true;
        } catch (FileNotFoundException e) {
        	CLog.exception(TAG, e);
            return false;
        } catch (IOException e) {
        	CLog.exception(TAG, e);
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                	CLog.exception(TAG, e);
                }
            }
        }
    }
}
