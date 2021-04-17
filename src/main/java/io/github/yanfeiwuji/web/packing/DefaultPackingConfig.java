package io.github.yanfeiwuji.web.packing;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanfeiwuji
 */
@Configuration
public class DefaultPackingConfig {

  @Bean
  @ConditionalOnMissingBean
  public ObjToPacking<Object> doPacking() {
    return o -> o;
  }
}
