package com.vcut.tool.apm.transform;

import org.gradle.api.Project;
import org.gradle.api.logging.Logger;

/**
 * @Description: java类作用描述
 * @Author: Andy
 * @CreateDate: 2021/11/7 16:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/11/7 16:31
 * @Warn: 更新说明
 * @Version: 1.0
 */
public final class Loger {

  private static Logger logger;

  public static void init(Project project){
    if (logger == null) {
      logger = project.getLogger();
    }
  }

  public static Logger getLogger() {
    return logger;
  }
}
