# web添加包装类

## 安装

```shell
mvn install
```

```xml

<dependency>
  <groupId>io.github.yanfeiwuji</groupId>
  <artifactId>web-packing</artifactId>
  <version>0.0.1-RELEASE</version>
</dependency>

```

## 添加你的返回类

```java
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yanfeiwuji
 */
@Data
@AllArgsConstructor
public class R<T> {
  boolean success;
  String msg;
  T data;

  public static <T> R<T> OK(T data) {
    return new R<>(true, "success", data);
  }
}

```

## 配置

```java
package com.yfwjt.test;

import io.github.yanfeiwuji.web.packing.ObjToPacking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@SpringBootApplication
@RestController
@RequestMapping
public class SpringDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringDemoApplication.class, args);
  }

  @Bean
  ObjToPacking<R> objToPacking() {
    return o -> R.OK(o);
  }

  @GetMapping("/pack")
  public R<List<String>> pack() {
    return R.OK(Collections.singletonList("123"));
  }

  @GetMapping("/noPack")
  public List<String> noPack() {
    return Collections.singletonList("123");
  }
  
}

```

## 效果
url http://localhost:3801/pack
url http://localhost:3801/noPack
```json 
{
    "success": true,
    "msg": "success",
    "data": [
        "123"
    ]
}
```