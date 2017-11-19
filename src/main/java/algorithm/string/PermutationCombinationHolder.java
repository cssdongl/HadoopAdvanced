package algorithm.string;

/**
 * Created by dongliang on 17/11/7.
 */
public class PermutationCombinationHolder {

    public static void main(String[] args) {

        String str = "abcd";
        combination(str.toCharArray());

    }

    static void combination(char[] chars){
        char[] newChars = new char[chars.length];

        for(int i = 0;i<newChars.length;i++){
            final int m = i + 1;
            combination(chars,chars.length,m,newChars,m);
        }
    }

    static void combination(char[] chars,int n,int m ,char[] subChars,int subn){

        if(m == 0){
            for(int i =0;i<subn;i++){
                System.out.print(subChars[i]);
            }
            System.out.println();
        }else{
            for(int i=n;i>=m;i--){
                subChars[m-1] = chars[i-1];
                combination(chars,i-1,m-1,subChars,subn);
            }
        }
    }


}
