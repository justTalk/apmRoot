package com.vcut.tool.apm.transform.thread;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.quinn.hunter.transform.HunterTransform;
import com.vcut.tool.apm.ApmExtension;
import java.io.IOException;
import java.util.Collection;
import org.gradle.api.Project;

/**
 * @Description: hook线程创建
 * @Author: Andy
 * @CreateDate: 2021/11/7 14:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/11/7 14:42
 * @Warn: 更新说明
 * @Version: 1.0
 */
public class ThreadHookTransform extends HunterTransform {

  ApmExtension apmExt;

  public ThreadHookTransform(Project project) {
    super(project);
    apmExt = project.getExtensions().create("apmExt", ApmExtension.class);
    this.bytecodeWeaver = new ThreadWeaver(apmExt);
    printCopyRight();
  }

  @Override public void transform(Context context, Collection<TransformInput> inputs,
      Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider,
      boolean isIncremental) throws IOException, TransformException, InterruptedException {
    bytecodeWeaver.setExtension(apmExt);
    super.transform(context, inputs, referencedInputs, outputProvider, isIncremental);
  }

  static void printCopyRight() {
    System.out.println();
    System.out.println("******************************************************************************");
    System.out.println("******                                                                  ******");
    System.out.println("******                欢迎使用 ThreadPoolTransform 编译插件                    ******");
    System.out.println("******                                                                  ******");
    System.out.println("******************************************************************************");
    System.out.println();
  }
}
