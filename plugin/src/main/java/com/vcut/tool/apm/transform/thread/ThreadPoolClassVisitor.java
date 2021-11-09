package com.vcut.tool.apm.transform.thread;

import com.vcut.tool.apm.ApmExtension;
import com.vcut.tool.apm.transform.Loger;
import org.gradle.api.logging.LogLevel;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @Description:
 * @Author: Andy
 * @CreateDate: 2021/11/7 15:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/11/7 15:03
 * @Warn: 更新说明
 * @Version: 1.0
 */
class ThreadPoolClassVisitor extends ClassVisitor {

  private ApmExtension apmExtension;

  public ThreadPoolClassVisitor(ClassVisitor classVisitor, ApmExtension apmExtension) {
    super(Opcodes.ASM6, classVisitor);
    this.apmExtension = apmExtension;
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName,
      String[] interfaces) {
    Loger.getLogger().log(LogLevel.ERROR, "visit: " + name);
    super.visit(version, access, name, signature, superName, interfaces);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
      String[] exceptions) {
    if (name.contains("newCachedThreadPool")){
      Loger.getLogger().log(LogLevel.ERROR, "visitMethod: " + name);
    }
    MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
    return mv == null ? null
        : new HookThreadPoolAdapter(null, null, name, access, descriptor, mv, this.apmExtension);
  }
}
