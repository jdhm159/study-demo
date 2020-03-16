package Generic;

//这三种写法各有不同

//下面这种写法限制最少，能接受 实现了Comparable接口的类型，包括原始类型Comparable、Comparable<Object>、Comparable<Integer>等
//public class GenericWildcard<T extends Comparable> {
//
//    public static final void main(String[] args){
//        Generic.GenericWildcard<Generic.ChildClass> childClassGenericWildcard = new Generic.GenericWildcard<>();
//    }
//}

//下面这种写法 要求 传入的类型参数可以从父类或者自身实现的Comparable接口，但不能实现原始类型Comparable
public class GenericWildcard<T extends Comparable<? super T>> {

    public static final void main(String[] args){
        GenericWildcard<ChildClass> childClassGenericWildcard = new GenericWildcard<>();
    }
}

//这个写法限制最多，要求类型参数自身实现Comparable<T>接口
//public class Generic.GenericWildcard<T extends Comparable<T>> {
//
//    public static final void main(String[] args){
//        Generic.GenericWildcard<Generic.ChildClass> childClassGenericWildcard = new Generic.GenericWildcard<>();
//    }
//}