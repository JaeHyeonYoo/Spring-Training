package hello.core.singleton;

public class SingletonService {

    //static 을 이용하여 객체를 하나만 생성한다.
    private static SingletonService instance = new SingletonService();

    //instance 를 호출하려면 getInstance 만을 이용하여 호출할 수 있다.
    public static SingletonService getInstance(){
        return instance;
    }

    //기본 생성자를 private 로 설정하여 외부에서 호출을 하지 못하게한다.
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
