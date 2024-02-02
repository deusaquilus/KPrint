import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.dokka.gradle.AbstractDokkaTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
  kotlin("multiplatform")

  //  `maven-publish`
//  id("io.github.gradle-nexus.publish-plugin") version "1.1.0"  // must be applied to root project
  signing
}

kotlin {
  jvm {
    jvmToolchain(11)
  }
  js {
    browser()
    nodejs()
  }

  linuxX64()
  linuxArm64()
  macosX64()
  macosArm64()
  mingwX64()
  androidNativeX64()
  androidNativeX86()
  androidNativeArm32()
  androidNativeArm64()
  iosX64()
  iosArm64()
  iosSimulatorArm64()
  tvosX64()
  tvosArm64()
  watchosX64()
  watchosArm32()
  watchosArm64()

  @OptIn(ExperimentalWasmDsl::class)
  wasmWasi()
  @OptIn(ExperimentalWasmDsl::class)
  wasmJs()

  @OptIn(ExperimentalWasmDsl::class)
  wasmWasi()
  @OptIn(ExperimentalWasmDsl::class)
  wasmJs()

  js {
    browser()
    nodejs()
  }

  sourceSets {
    commonMain {
      dependencies {
        api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.2")
      }
    }

    commonTest {
      dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }

    val jvmMain by getting {
      dependencies {
        api(kotlin("reflect"))
      }
    }

    val jvmTest by getting {
      dependencies {
      }
    }
  }
}

tasks.named<Test>("jvmTest") {
  useJUnitPlatform()
}

tasks.withType<AbstractTestTask>().configureEach {
  testLogging {
    showStandardStreams = true
    showExceptions = true
    exceptionFormat = TestExceptionFormat.SHORT
    events(TestLogEvent.STARTED, TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
  }
}
