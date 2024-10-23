plugins {
	kotlin("jvm") version "2.0.10"
	kotlin("plugin.spring") version "2.0.10"

	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"

	id("info.solidsoft.pitest") version "1.15.0"
	id("org.unbroken-dome.test-sets") version "4.1.0"
	id("io.gitlab.arturbosch.detekt") version "1.23.7"
	id("jacoco")
	id("org.sonarqube") version "5.1.0.4882"
}

group = "com.creditas"
version = "0.0.1"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

val assertJVersion = "3.26.3"
var detektVersion = "1.23.7"
var mockkVersion = "1.13.12"

testSets {
	"integrationTest"()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	compileOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.assertj:assertj-core:$assertJVersion")
	testImplementation("io.mockk:mockk:$mockkVersion")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	detekt("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
	detekt("io.gitlab.arturbosch.detekt:detekt-cli:$detektVersion")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

detekt {
	toolVersion = detektVersion
	autoCorrect = true
}


sonar {
	val projectKey = System.getenv("SONAR_PROJECT_KEY") ?: ""
	val organization = System.getenv("SONAR_ORGANIZATION") ?: ""

	properties {
		property("sonar.projectKey", projectKey)
		property("sonar.language", "kotlin")
		property("sonar.organization", organization)
		property("sonar.host.url", "https://sonarcloud.io")
		property("sonar.exclusions", "**/creditas/**/*.java," +
				"**/creditas/**/*.kts," +
				"**/loan/configuration/*," +
				"**/loan/LoanApplication.kt," +
				"**/loan/**/resources/*,")
	}
}

fun ignorePackagesInJacocoReport(classDirectories: ConfigurableFileCollection) {
	classDirectories.setFrom(
		files(
			classDirectories.files.map {
				fileTree(it).apply {
					exclude(
						"**/creditas/**/*.java",
						"**/creditas/**/*.kts",
						"**/loan/configuration/*",
						"**/loan/LoanApplication*",
						"**/loan/**/resources/*"
					)
				}
			}
		)
	)
}

tasks.test {
	finalizedBy("jacocoReport")
}

tasks.register<JacocoReport>("jacocoReport") {
	description = "Generates the HTML documentation for this project"
	group = JavaBasePlugin.DOCUMENTATION_GROUP
	sourceSets(sourceSets.main.get())
	executionData(fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec"))

	reports {
		xml.required.set(true)
		csv.required.set(false)
		html.required.set(true)
	}

	ignorePackagesInJacocoReport(classDirectories)
}
