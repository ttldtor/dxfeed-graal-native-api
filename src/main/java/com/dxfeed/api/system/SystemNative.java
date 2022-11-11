package com.dxfeed.api.system;

import static com.dxfeed.api.exception.ExceptionHandlerReturnMinusOne.EXECUTE_SUCCESSFULLY;

import com.dxfeed.api.BaseNative;
import com.dxfeed.api.exception.ExceptionHandlerReturnMinusOne;
import com.dxfeed.api.exception.ExceptionHandlerReturnNullWord;
import com.dxfeed.event.market.Mapper;
import com.dxfeed.event.market.StringMapperUnlimitedStore;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;

@CContext(Directives.class)
public final class SystemNative extends BaseNative {

  private static final Mapper<String, CCharPointer> STRING_MAPPER = new StringMapperUnlimitedStore();

  @CEntryPoint(
      name = "dxfg_system_set_property",
      exceptionHandler = ExceptionHandlerReturnMinusOne.class
  )
  public static int setSystemProperty(
      final IsolateThread ignoredThread,
      final CCharPointer key,
      final CCharPointer value
  ) {
    System.setProperty(toJavaString(key), toJavaString(value));
    return EXECUTE_SUCCESSFULLY;
  }

  @CEntryPoint(
      name = "dxfg_system_get_property",
      exceptionHandler = ExceptionHandlerReturnNullWord.class
  )
  public static CCharPointer getSystemProperty(
      final IsolateThread ignoredThread,
      final CCharPointer key
  ) {
    return STRING_MAPPER.nativeObject(System.getProperty(toJavaString(key)));
  }

  @CEntryPoint(
      name = "dxfg_system_release_property",
      exceptionHandler = ExceptionHandlerReturnMinusOne.class
  )
  public static int releaseSystemProperty(
      final IsolateThread ignoredThread,
      final CCharPointer value
  ) {
    STRING_MAPPER.delete(value);
    return EXECUTE_SUCCESSFULLY;
  }
}
