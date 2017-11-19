package algorithm.dp;

/**
 * Created by dongliang on 17/10/29.
 * get the longest common str between two strings
 */
public class LongestCommonSubStr {

    public int[][] getDp(String str1, String str2) {
        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();

        int[][] dp = new int[str1Arr.length][str2Arr.length];

        for (int i = 0; i < str1Arr.length; i++) {
            if (str1Arr[i] == str2Arr[0]) {
                dp[i][0] = 1;
            }
        }

        for (int j = 1; j < str2Arr.length; j++) {
            if (str2Arr[j] == str1Arr[0]) {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < str1Arr.length; i++) {
            for (int j = 1; j < str2Arr.length; j++) {
                if (str1Arr[i] == str2Arr[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }


        return dp;
    }


    public static void main(String[] args) {

    }
}
