package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        System.out.println("find prototypeBean1");
        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class); //요청이 들어오면 새로운 빈을 생성해낸다.
        System.out.println("find prototypeBean2");
        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);

        System.out.println("protoTypeBean1 = " + protoTypeBean1);
        System.out.println("protoTypeBean2 = " + protoTypeBean2);

        //prototype scope 에서는 서로다른 객체의 참조값임을 알 수 있다.
        Assertions.assertThat(protoTypeBean1).isNotSameAs(protoTypeBean2);
        ac.close();
    }

    @Scope("prototype")
    static class ProtoTypeBean{
        @PostConstruct
        public void init(){
            System.out.println("ProtoTypeBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("ProtoTypeBean.destroy");
        }
    }

}
