package com.vcut.tool.apm.transform.thread;

import com.vcut.tool.apm.ApmExtension;
import com.vcut.tool.apm.transform.Loger;
import org.gradle.api.logging.LogLevel;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.LocalVariablesSorter;

public final class HookThreadPoolAdapter extends LocalVariablesSorter implements Opcodes {

    private String classNamePath;
    private String className;
    private String superClassName;
    private String methodDes;
    private String methodName;
    private ApmExtension hookExtension;

    public HookThreadPoolAdapter(String className, String superClassName, String methodName, int access,
                             String desc,
                             MethodVisitor mv, ApmExtension hookExtension) {
        super(Opcodes.ASM5, access, desc, mv);
        /*this.classNamePath = className.replace(".", "/");
        this.className = className;
        this.superClassName = superClassName;
        this.methodName = methodName;
        this.methodDes = desc;*/
        this.hookExtension = hookExtension;
    }


    @Override
    public void visitLdcInsn(Object value) {

        // 华为里面反射访问了 高通的 TelephonyManager
        if ("android.telephony.MSimTelephonyManager".equals(value)) {
            // 这里，为了抛出 ClassNotFoundException
            value = "test.test.MSimTelephonyManager";
        }

        // adb shell 获取 mac hook
        if ("cat /sys/class/net/wlan0/address ".equals(value)) {
            // 直接打印unknown
            value = "echo unknown ";
        }

        super.visitLdcInsn(value);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String methodName, String desc, boolean itf) {
        if ("newCachedThreadPool".equals(methodName) || owner.contains("Executors")) {
            Loger.getLogger().log(LogLevel.ERROR, "visitMethodInsn: " + owner + " method: " + methodName);
        }
        super.visitMethodInsn(opcode, owner, methodName, desc, itf);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle,
        Object... bootstrapMethodArguments) {
        Loger.getLogger().log(LogLevel.ERROR, "visitInvokeDynamicInsn: " + name + " descriptor: " + descriptor);
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle,
            bootstrapMethodArguments);
    }
}