package hello.core.scope;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); //새로운 빈을 생성하여 count 0 에 ++을 해준다.
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); //새로운 빈을 생성하여 count 에 1을 ++ 해주기 때문에 1이된다.
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

        prototypeBean1.destroy(); // prototype scope 는 수동으로 소멸할 수 있다.
        prototypeBean2.destroy();
    }

    @Test
    void singletonClientUsePrototype(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        System.out.println("clientBean1 = " + clientBean1);   //singleton 으로 관리가 되어 같은 참조 값으로 반환해준다.
        assertThat(clientBean1.logic()).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        System.out.println("clientBean2 = " + clientBean2);
        assertThat(clientBean2.logic()).isEqualTo(2);

        ac.close();
    }


    static class ClientBean{

        private final PrototypeBean prototypeBean;

        public ClientBean(PrototypeBean prototypeBean) {  // 이때 스프링 빈을 만들어 반환해주고 프로토타입이기 때문에 앞으로 관리는 싱글톤에서 한다.
            this.prototypeBean = prototypeBean;
        }

        public int logic(){
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
        @PostConstruct
        public void init(){
            System.out.println("ClientBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("ClientBean.destroy");
        }
    }

    @Scope("prototype")
    static class PrototypeBean{

        private int count;

        public void addCount(){
            count ++;
        }

        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
