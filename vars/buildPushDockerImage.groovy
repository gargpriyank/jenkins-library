#!/usr/bin/groovy

/**
 * Build and push docker image.
 *
 * Examples:
 * buildPushDockerImage(appName: 'myapp', imageTag: 'latest', dockerRegistry: 'docker.io', dockerRepo: 'sample-repo')
 *
 * @param appName
 * @param imageTag
 * @param dockerRegistry
 * @param dockerRepo
 */
def call(args) {

    def appName = args.appName
    def imageTag = args.imageTag
    def dockerRegistry = args.dockerRegistry
    def dockerRepo = args.dockerRepo

    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'registry_creds', usernameVariable: 'userName', passwordVariable:
            'password']]) {
        sh "docker login $dockerRegistry -u $userName -p $password"
    }
    sh "docker build -t $dockerRegistry/$dockerRepo/$appName:$imageTag -f deploy/Dockerfile ."
    sh "docker push $dockerRegistry/$dockerRepo/$appName:$imageTag"
    sh "docker logout $dockerRegistry"
}
