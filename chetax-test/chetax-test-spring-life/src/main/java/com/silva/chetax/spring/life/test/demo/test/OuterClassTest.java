package com.silva.chetax.spring.life.test.demo.test;

public class OuterClassTest {
    // 怎么创建静态内部类和非静态内部类的实例
    public static void main(String args[]){
       // static 就是一种静态资源声明标识符，在外部访问某类中资源的时候，只要该资源被其标识，则不需要某类的实例，通过类名直接访问即可。
       // 创建静态内部类的实例
       OuterClass.NestedStaticClass printer = new OuterClass.NestedStaticClass();

       // 创建静态内部类的非静态方法
       printer.printMessage();   

       // 为了创建非静态内部类，我们需要外部类的实例
       OuterClass outer = new OuterClass();        
       OuterClass.InnerClass inner  = outer.new InnerClass();

       // 调用非静态内部类的非静态方法
       inner.display();

       // 我们也可以结合以上步骤，一步创建的内部类实例
       OuterClass.InnerClass innerObject = new OuterClass().new InnerClass();

       // 同样我们现在可以调用内部类方法
       innerObject.display();
       
    }
}
