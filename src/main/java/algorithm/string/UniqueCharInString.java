package algorithm.string;

/**
 * Created by dongliang on 17/11/4.
 */
public class UniqueCharInString {
    public static void main(String[] args) {

        String str = "abc";
        if(UniqueCharInString.isUnique(str)){
            System.out.println(String.format("%s character is unique",str));
        }

    }

    public static boolean isUnique(String str) {

        char[] charArr = str.toCharArray();

        if (charArr == null) {
            return false;
        }

        boolean[] map = new boolean[256];

        for (int i = 0; i < charArr.length; i++) {
            if(map[charArr[i]]){
                return false;
            }
            map[charArr[i]] = true;
        }

        return true;
    }
}
