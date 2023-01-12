package hello.core.singleton;

public class StatefulService {

    //private int price; //상태를 항상 유지하는 필드

    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        return price;  //맴버마다 price를 확인하기 위해 무상태로 설계를한다.
    }

//    public int getPrice(){
//        return price;
//    }

}
