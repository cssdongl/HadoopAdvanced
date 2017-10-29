package algorithm.string;

/**
 * Created by dongliang on 17/10/29.
 * To judge two string if it is distorion(means the character in
 * two string appear same types and times such as '123','321'
 */
public class JudgeDistort {


    private boolean isDistort(String str1, String str2) {

        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }


        int[] frequencyMap = new int[256];

        char[] str1CharArray = str1.toCharArray();

        char[] str2CharArray = str2.toCharArray();


        for (int i = 0; i < str1CharArray.length; i++) {
            frequencyMap[str1CharArray[i]]++;
        }

        for (int j = 0; j < str2CharArray.length; j++) {
            if (frequencyMap[str2CharArray[j]]-- == 0) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        String str1 = "acbcde";

        String str2 = "ecdbac";

        String str3 = "abcdde";

        JudgeDistort jd = new JudgeDistort();

        if (jd.isDistort(str1, str2)) {
            System.out.println("the str1 and str2 is distortion");
        }

        if (!jd.isDistort(str1, str3)) {
            System.out.println("the str1 and st3 is not distortion");
        }


    }
}
