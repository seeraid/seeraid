package org.seeraid.sample;

import java.util.Enumeration;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RVector;
import org.rosuda.JRI.Rengine;

public class RJavaTest {

    public static void main(String[] args) {
    	
    	System.out.println(System.getenv().get("java.library.path"));
//    	System.getenv().put("java.library.path", "/home/koder/.gradle/caches/modules-2/files-2.1/org.nuiton.thirdparty/JRI/0.9-6/f20fb2e559f8c73e66b21c70cdd72c91ffe53bf4/JRI-0.9-6.jar");
    	System.setProperty("java.library.path", "/home/koder/.gradle/caches/modules-2/files-2.1/org.nuiton.thirdparty/JRI/0.9-6/f20fb2e559f8c73e66b21c70cdd72c91ffe53bf4/JRI-0.9-6.jar");

        Rengine re=new Rengine(args, false, null);
        REXP x;
        re.eval("print(1:10/3)");
        System.out.println(x=re.eval("iris"));
        RVector v = x.asVector();
        if (v.getNames()!=null) {
            System.out.println("has names:");
            for (Enumeration e = v.getNames().elements() ; e.hasMoreElements() ;) {
                System.out.println(e.nextElement());
            }
        }

        if (true) {
            System.out.println("Now the console is yours ... have fun");
            re.startMainLoop();
        } else {
            re.end();
            System.out.println("end");
        }
    }
}