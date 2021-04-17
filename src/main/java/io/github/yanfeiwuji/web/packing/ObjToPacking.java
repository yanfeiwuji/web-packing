package io.github.yanfeiwuji.web.packing;

/**
 * @author yanfeiwuji
 * @date 2021/4/17 12:08 下午
 */
@FunctionalInterface
public interface ObjToPacking<R> {
  R packing(Object o);

  default R doPacking(Object o) {
    return packing(o);
  }
}
