#!/usr/bin/groovy

/**
 * Build the JAR artifact using maven.
 *
 * Example:
 * buildJarMaven(mvnArgs: '-DskipTests', checkMavenSettings: true)
 *
 * @param mvnArgs
 */
def call(args) {

    def mvnArgs = args.mvnArgs
    def checkMavenSettings = args.checkMavenSettings ?: false

    if (checkMavenSettings && mvnArgs) {
        configFileProvider([configFile(fileId: 'maven_settings', variable: 'maven_settings')]) {
            sh "-s $maven_settings clean install $mvnArgs"
        }
    } else if (checkMavenSettings) {
        configFileProvider([configFile(fileId: 'maven_settings', variable: 'maven_settings')]) {
            sh "mvn -s $maven_settings clean install"
        }
    } else if (mvnArgs) {
        sh "mvn clean install $mvnArgs"
    } else {
        sh "mvn clean install"
    }
}
