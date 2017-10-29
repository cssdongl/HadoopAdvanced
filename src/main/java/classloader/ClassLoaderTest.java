package classloader;

/**
 * Created by dongliang on 17/10/14.
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader c1 = Test.class.getClassLoader();
        System.out.println("the classloader is:" + c1.toString());
        System.out.println("the parent classloader is:" + c1.getParent().toString());
    }
}
