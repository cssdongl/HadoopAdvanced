package algorithm.string;

/**
 * Created by dongliang on 17/10/30.
 */
public class MaxNoRepeatSubStr {
    public static void main(String[] args) {

    }

    public int maxUnique(String str){
        if(null == str || str == ""){
            return 0;
        }
        char[] charArr = str.toCharArray();

        int[] map = new int[256];

        for(int i=0;i<256;i++){
            map[i] = -1;
        }

        int len = 0;
        int pre = -1;
        int cur = 0;

        for(int i=0;i!=charArr.length;i++){
            pre = Math.max(map[charArr[i]],pre);
            cur = i - pre;

            len = Math.max(len,cur);

            map[charArr[i]] = i;
        }
        return len;
    }
}
