package com.dxfeed.api.events;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(Directives.class)
@CStruct("dxfg_event_type_t")
public interface DxfgEventType extends PointerBase {

  @CField("kind")
  int getKind();

  @CField("kind")
  void setKind(int kind);
}
