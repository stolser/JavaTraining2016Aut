package com.stolser.javatraining.designpatterns.structural.flyweight;

import com.stolser.javatraining.designpatterns.structural.flyweight.entity.Code;
import com.stolser.javatraining.designpatterns.structural.flyweight.entity.platform.Platform;
import com.stolser.javatraining.designpatterns.structural.flyweight.entity.platform.PlatformFactory;

public class Client {
    public static void main(String[] args) {
        Code code = new Code();
        code.setCode("C Code...");
        Platform platform = PlatformFactory.getPlatformInstance("C");
        platform.execute(code);
        System.out.println("*************************");

        code = new Code();
        code.setCode("C Code2...");
        platform = PlatformFactory.getPlatformInstance("C");
        platform.execute(code);
        System.out.println("*************************");

        code = new Code();
        code.setCode("JAVA Code...");
        platform = PlatformFactory.getPlatformInstance("JAVA");
        platform.execute(code);
        System.out.println("*************************");

        code = new Code();
        code.setCode("JAVA Code2...");
        platform = PlatformFactory.getPlatformInstance("JAVA");
        platform.execute(code);
        System.out.println("*************************");

        code = new Code();
        code.setCode("RUBY Code...");
        platform = PlatformFactory.getPlatformInstance("RUBY");
        platform.execute(code);
        System.out.println("*************************");

        code = new Code();
        code.setCode("RUBY Code2...");
        platform = PlatformFactory.getPlatformInstance("RUBY");
        platform.execute(code);
    }
}
