#!/usr/bin/groovy

/**
 * Build and push docker image.
 *
 * Examples:
 * buildPushDockerImage(appName: 'myapp', imageTag: 'latest', dockerRegistry: 'docker.io', dockerRepo: 'sample-repo',
 * dockerFilePath: 'deploy/Dockerfile', buildAh: true)
 *
 * @param appName
 * @param imageTag
 * @param dockerRegistry
 * @param dockerRepo
 * @param dockerFilePath
 * @param buildAh
 */
def call(args) {

    def appName = args.appName
    def imageTag = args.imageTag
    def dockerRegistry = args.dockerRegistry
    def dockerRepo = args.dockerRepo
    def dockerFilePath = args.dockerFilePath ?: 'deploy/Dockerfile'
    def buildAh = args.buildAh ?: true

    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'registry_creds', usernameVariable: 'userName', passwordVariable:
            'password']]) {
        sh "docker login $dockerRegistry -u $userName -p $password"
    }
    if (buildAh) {
        sh "buildah -t $dockerRegistry/$dockerRepo/$appName:$imageTag -f $dockerFilePath ."
    } else {
        sh "docker build -t $dockerRegistry/$dockerRepo/$appName:$imageTag -f $dockerFilePath ."
    }
    sh "docker push $dockerRegistry/$dockerRepo/$appName:$imageTag"
    sh "docker logout $dockerRegistry"
}
