public class StringInternTest {

    public static void main(String[] args) {
        String str2 = new String("stu") + new String("dy");
        System.out.println(str2.intern() == str2);                      //1.3下实测：false      1.8下实测：true
        System.out.println(str2 == "study");                            //1.3下实测：false      1.8下实测：true

        String str1 = new String("ja") + new String("va");
        System.out.println(str1.intern() == str1);                      //1.3下实测：false      1.8下实测：false
        System.out.println(str1 == "java");                             //1.3下实测：false      1.8下实测：false
    }
}
