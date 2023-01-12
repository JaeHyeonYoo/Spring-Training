package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(  //수동으로 설정등록한 AppConfig를 제외
        //basePackages = "hello.core.member",   //member 패키지의 하위만 조회한다.
        //basePackageClasses = AutoAppConfig.class, // =package hello.core; 해당 클래스의 패키지로 부터 하위 패키지를 탐색한다.
        //디폴트 시 프로젝트의 가장 상단 패키지를 지정하는 것이 관례이다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
