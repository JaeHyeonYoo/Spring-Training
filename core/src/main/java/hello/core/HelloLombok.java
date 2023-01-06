package hello.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HelloLombok {
    private String name;
    private int age;
    public static void main(String[] args) {

        //Lombok 의 @Setter @Getter 기능으로 코드를 따로 추가하지 않아도 자동으로 애너테이션이 코드를 생성해준다.
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("hello");
        System.out.println("helloLombok = " + helloLombok.getName());

    }
}
