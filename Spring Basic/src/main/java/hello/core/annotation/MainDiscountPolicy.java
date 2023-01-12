package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
//@Qualifier 를 재정의 하여 사용한다.
//사용하려는 빈에 직접 사용해도되지만 만약 문자를 타이핑하다가 오타를 내도 오류가 나지 않고 컴파일되기 때문에 실수를 방지할 수 있다.
