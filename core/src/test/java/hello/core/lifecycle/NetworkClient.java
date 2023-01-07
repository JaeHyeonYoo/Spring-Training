package hello.core.lifecycle;

public class NetworkClient {
    
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }
    public NetworkClient() {
        System.out.println("생성자 호출 시 url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    //서버 시작 시 호출
    public void connect(){
        System.out.println("connect = " + url);
    }

    public void call(String message){
        System.out.println("call : "+ url + " meassage = " + message);
    }

    //서비 종료 시 호출
    public void disconnect(){
        System.out.println("close : "+url);
    }
}
