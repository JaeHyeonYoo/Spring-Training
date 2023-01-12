package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope("request") 2개 이상의 파라미터를 넣기 위해 앞에 value 를 넣어줘야한다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //TARGET.CLASS 가짜 MyLogger 를 만들어준다.
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid+ " ] [ "+ requestURL +" ] "+ message);
    }

    @PostConstruct // 빈이 생성되는 시점에 초기화도 진행이되어 uuid 에 값을 넣어준다.
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create. "+ this);
    }
    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean close. ");
    }


}
