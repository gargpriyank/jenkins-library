#!/usr/bin/groovy

/**
 * Login to OpenShift cluster.
 *
 * Example:
 * loginOS(clusterURL: 'https://')
 *
 * @param clusterURL
 */
def call(args) {

    def clusterURL = args.clusterURL

    withCredentials([string(credentialsId: 'os-jenkins-sa-token', variable: 'token')]) {
        sh "oc login $clusterURL --token=$token"
    }
}
