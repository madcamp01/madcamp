pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven {url=java.net.URI("https://devrepo.kakao.com/nexus/repository/kakaomap-releases/")}
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url=java.net.URI("https://devrepo.kakao.com/nexus/repository/kakaomap-releases/") }
    }
}

rootProject.name = "Madcamp01"
include(":app")
