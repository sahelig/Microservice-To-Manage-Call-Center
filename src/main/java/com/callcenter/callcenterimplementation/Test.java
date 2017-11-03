package com.callcenter.callcenterimplementation;

import java.util.Arrays;

public class Test {
    Integer number_of_calls;

    public int[][] getJe() {
        return je;
    }

    public void setJe(int[][] je) {
        this.je = cloneArray(je);
    }

    public int[][] getSe() {
        return se;
    }

    public void setSe(int[][] se) {
        this.se = cloneArray(se);
    }

    public int[] getMgr() {
        return mgr;
    }

    public void setMgr(String mgr) {
        String arr[] = mgr.split(",");
        this.mgr = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
    }

    int je[][];
    int se[][];
    int mgr[];


    public Integer getNumber_of_calls() {
        return number_of_calls;
    }

    @Override
    public String toString() {
        return "Test{" +
                "number_of_calls=" + number_of_calls +
                '}';

    }
    public static int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }
    public void setNumber_of_calls(Integer number_of_calls) {
        this.number_of_calls = number_of_calls;
    }
}
