package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final ObjectProvider<MyLogger> myLoggerProvider;  // request 호출이 없는 상태에서 주입이 일어나려 하기 때문에 에러발생.
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();

        MyLogger myLogger = myLoggerProvider.getObject();  //이곳에서 DL을 해주기 때문에 가능하다.
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller-test");
        logDemoService.logic("service-test");

        return "ok";
    }


}
