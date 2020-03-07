import java.util.ArrayList;
import java.util.List;

/**
 <b>ObjectToStringTest。</b>
 <p><b>详细说明：测试Object.toString</b></p>
 <!-- 在此添加详细说明 -->
 无。
 <p><b>修改列表：</b></p>
 <table width="100%" cellSpacing=1 cellPadding=3 border=1>
 <tr bgcolor="#CCCCFF"><td>序号</td><td>作者</td><td>修改日期</td><td>修改内容</td></tr>
 <!-- 在此添加修改列表，参考第一行内容 -->
 <tr><td>1</td><td>Oliver</td><td>2010-11-26 下午04:33:22</td><td>建立类型</td></tr>
 </table>
 @version 1.0
 @author Oliver
 @since 1.0
 */
public class ObjectToStringTest {
    /**
     * 默认值。
     */
    private static final int SIZE=100000;

    public static void main(String[] args)
    {
    //创建列表存放对象
    List<Object> list = new ArrayList<Object>();
    int existNumber=0;
    //新建SIZE个对象，如果toStirng代表的是内存地址，地址是不会重复的，
    //那么list中应该不会存在重复的元素。
    //list的大小应该为SIZE
    for(int i=0;i<SIZE;i++){
    Object obj = new Object();
    if(list.contains(obj.toString())){
    System.out.println("对象："+obj.toString()+"已存在!");
    existNumber++;
    }else
    list.add(obj.toString());
    }
    System.out.println("列表List的大小:"+list.size());
    System.out.println("重复元素的个数："+existNumber);
    System.out.println("===============华丽的分割线===============");
    //清空list
    list.clear();
    existNumber=0;
    //新建一个对象的时候，变量名是对这个对象的应用（相当于对象的"地址"）
    //利用这个原理，我们再测试
    for(int i=0;i<SIZE;i++){
    Object obj = new Object();
    if(list.contains(obj)){
    System.out.println("对象："+obj+"已存在!");
    existNumber++;
    }else
    list.add(obj.toString());
    }
    System.out.println("列表List的大小:"+list.size());
    System.out.println("重复元素的个数："+existNumber);
    }
    }