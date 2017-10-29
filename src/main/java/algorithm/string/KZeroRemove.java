package algorithm.string;


/**
 * Created by dongliang on 17/10/29.
 */
public class KZeroRemove {
    public static void main(String[] args) {
        KZeroRemove kr = new KZeroRemove();
        String subStr = kr.removeKZerosInStr("A0000B000", 3);
        System.out.println("the substr after remove the K zero is:" + subStr);

    }

    public String removeKZerosInStr(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }

        char[] chars = str.toCharArray();
        int count = 0, start = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                count++;
                start = start == -1 ? i : start;
            } else {
                if (count == k) {
                    while (count-- != 0) {
                        chars[start++] = 0;
                    }
                }
                count = 0;
                start = -1;
            }
        }

        if (count == k) {
            while (count-- != 0) {
                chars[start++] = 0;
            }
        }
        return String.valueOf(chars);
    }

}
