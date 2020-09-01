#!/usr/bin/groovy

/**
 * Cleanup all the dangling docker images of the application.
 *
 * Examples:
 * cleanUpDockerNoTagImages(appName: 'myapp', dockerRegistry: 'docker.io')
 *
 * @param appName
 * @param dockerRegistry
 */
def call(args) {

    def appName = args.appName
    def dockerRegistry = args.dockerRegistry

    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'registry_creds', usernameVariable: 'userName', passwordVariable:
            'password']]) {
        sh "docker login $dockerRegistry -u $userName -p $password"
    }
    sh "docker rmi `docker images $appName -f dangling=true --quiet` | true > /dev/null"
    sh "docker logout $dockerRegistry"
}
