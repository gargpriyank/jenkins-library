#!/usr/bin/groovy

/**
 * Build the JAR artifact using maven.
 *
 * Example:
 * buildJarMaven(mvnArgs: '-DskipTests')
 *
 * @param mvnArgs
 */
def call(args) {

    def mvnArgs = args.mvnArgs

    configFileProvider([configFile(fileId: 'maven_settings', variable: 'maven_settings')]) {
        sh "mvn -s $maven_settings clean install $mvnArgs"
    }
}
