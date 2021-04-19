package io.github.yanfeiwuji.web.packing;

import java.lang.annotation.*;


/**
 *  // 不打包
 * @author yanfeiwuji
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoPacking {

}
