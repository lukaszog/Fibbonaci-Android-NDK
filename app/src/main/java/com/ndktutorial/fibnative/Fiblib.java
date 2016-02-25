package com.ndktutorial.fibnative;

/**
 * Created by Lukasz on 2016-02-22.
 */

/*
javah -jni -classpath ..\..\build\intermediates\classes\debug -d jni/ com.ndktutorial.fibnative.Fiblib
*/


public class Fiblib {

    //rekurencyjnie
    public static long fibJR(long n)
    {
        return n <= 0 ? 0 : n == 1 ? 1 : fibJR(n-1) + fibJR(n-2);
    }

    public static long fibJI(long n)
    {
        long prev = -1;
        long result = 1;

        for(long i=0;i<n;i++)
        {
            long sum = result+prev;
            prev = result;
            result = sum;
        }
        return result;
    }

    //rekurencyjnie jni
    public native static long fibNR(long n);
    //iteracyjnie jni
    public native static long fibNI(long n);



    static {
        System.loadLibrary("com_ndktutorial_fibnative_Fiblib");
    }
}
