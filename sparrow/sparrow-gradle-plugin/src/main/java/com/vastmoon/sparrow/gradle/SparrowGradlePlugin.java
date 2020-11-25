package com.vastmoon.sparrow.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.springframework.boot.gradle.plugin.SpringBootPlugin;

/**
 * <p> ClassName: SparrowGradlePlugin
 * <p> Description: 插件
 *
 * @author yousuf 2020/11/11
 */
public class SparrowGradlePlugin implements Plugin<Project> {
    public static final String BOM_COORDINATES = "com.vastmoon.com.vastmoon.sparrow:com.vastmoon.sparrow-dependencies:";
    @Override
    public void apply(Project project) {
        project.getPlugins().apply(SpringBootPlugin.class);
    }
}
