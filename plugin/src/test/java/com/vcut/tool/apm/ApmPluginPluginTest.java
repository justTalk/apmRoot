/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.vcut.tool.apm;

import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.api.Project;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A simple unit test for the 'com.vcut.tool.apm.greeting' plugin.
 */
public class ApmPluginPluginTest {
    @Test public void pluginRegistersATask() {
        // Create a test project and apply the plugin
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("com.vcut.tool.apm.greeting");

        // Verify the result
        assertNotNull(project.getTasks().findByName("greeting"));
    }
}
