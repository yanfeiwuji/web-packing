package io.github.yanfeiwuji.web.packing;

/**
 * @author yanfeiwuji
 */
@FunctionalInterface
public interface ObjToPacking<R> {
  R packing(Object o);

  default R doPacking(Object o) {
    return packing(o);
  }
}
