package com.android.binarnetworking.utils

import java.util.*

class tes {

    //print hello world
    fun printHelloWorld() {
        println("Hello World")
    }
    //that, given an array A of N integers, return true if A contains at least two elements which differ by 1, and false otherwise.
    fun containsTwoElementsDiffByOne(A: IntArray): Boolean {
        var count = 0

        //sort list from smallest to largest
        A.sort()

        A.forEach {
            if (it == A[count] + 1) {
                count++
            }
        }

//        for (i in 0 until A.size - 1) {
//            if (A[i] + 1 == A[i + 1]) {
//                count++
//            }
//            if (i == A.size-1) print("${A[i]}")
//        }
        return count >= 1
    }


    fun solution(A: IntArray): Int {
        val n = A.size
        var ans = 0
        for (i in 0 until n - 1) {
            val sum = A[i] + A[i + 1]
            ans = Math.max(ans, 1 + getCount(i + 2, sum, A))
        }
        return ans
    }

    private fun getCount(idx: Int, sum: Int, arr: IntArray): Int {
        val n = arr.size
        if (idx >= n - 1) {
            return 0
        }
        var count = 0
        if (arr[idx] + arr[idx + 1] == sum) count = 1 + getCount(idx + 2, sum, arr)
        return Math.max(count, getCount(idx + 1, sum, arr))
    }

    fun maxnumber(n: Int, k: Int): Int {
        var maxValue = Int.MIN_VALUE
        val stringValue = Integer.toString(n)
        for (i in 0 until stringValue.length) {
            if (stringValue[i] == '5') {
                val stringToCheck = stringValue.substring(0, i) + stringValue.substring(i + 1)
                val intToCheck = stringToCheck.toInt()
                maxValue = if (intToCheck > maxValue) intToCheck else maxValue
            }
        }
        //System.out.println(maxValue);
        //System.out.println(maxValue);
        return maxValue
    }

    fun largestSequence(A: Int, B: Int, C:Int) : String {
        var max = 0
        var maxString = ""
        for (i in A..B) {
            for (j in B..C) {
                val stringToCheck = Integer.toString(i) + Integer.toString(j)
                val intToCheck = stringToCheck.toInt()
                if (intToCheck > max) {
                    max = intToCheck
                    maxString = stringToCheck
                }
            }
        }
        return maxString
    }

    fun longestDiverseString(a: Int, b: Int, c: Int): String? {
        var a = a
        var b = b
        var c = c
        val sb = StringBuilder()
        val size = a + b + c
        var A = 0
        var B = 0
        var C = 0
        for (i in 0 until size) {
            if (a >= b && a >= c && A != 2 || B == 2 && a > 0 || C == 2 && a > 0) {
                sb.append("a")
                a--
                A++
                B = 0
                C = 0
            } else if (b >= a && b >= c && B != 2 || A == 2 && b > 0 || C == 2 && b > 0) {
                sb.append("b")
                b--
                B++
                A = 0
                C = 0
            } else if (c >= a && c >= b && C != 2 || B == 2 && c > 0 || A == 2 && c > 0) {
                sb.append("c")
                c--
                C++
                A = 0
                B = 0
            }
        }
        return sb.toString()
    }



}
