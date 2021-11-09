package com.vcut.tool.apm.transform.thread;

import com.quinn.hunter.transform.asm.BaseWeaver;
import com.vcut.tool.apm.ApmExtension;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * @Description: java类作用描述
 * @Author: Andy
 * @CreateDate: 2021/11/7 15:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/11/7 15:01
 * @Warn: 更新说明
 * @Version: 1.0
 */
public class ThreadWeaver extends BaseWeaver {

  private ApmExtension apmExtension;

  public ThreadWeaver(ApmExtension apmExtension){
    super();
    this.apmExtension = apmExtension;
  }

  @Override protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
    return new ThreadPoolClassVisitor(classWriter, apmExtension);
  }
}
