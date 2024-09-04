import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.hilt) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.detekt) apply false
}

val kotlinPluginIds = listOf(
    libs.plugins.kotlin.android,
)
    .map { plugin ->
        plugin.get().pluginId
    }

allprojects
    .onEach { project ->
        project.afterEvaluate {
            val hasKotlinPlugin = kotlinPluginIds.any { pluginId ->
                project.plugins.hasPlugin(pluginId)
            }

            if (hasKotlinPlugin.not()) return@afterEvaluate

            project.plugins.apply(libs.plugins.detekt.get().pluginId)
            project.extensions.configure<DetektExtension> {
                config.setFrom(rootProject.files("config/detekt/detekt.yml"))
            }
        }
    }
