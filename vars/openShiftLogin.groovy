#!/usr/bin/groovy

/**
 * Login to the OpenShift account.
 * Assumption: OpenShift account credentials "openshift-creds" is already set in Jenkins credentials.
 *
 * Example:
 * openShiftLogin(hostURL: 'https://', project: "myproject")
 *
 * @param hostURL
 * @param project
 */

def call(args) {
    def hostURL = args.hostURL
    def project = args.project

    withCredentials([string(credentialsId: 'openshift-creds', variable: 'token')]) {
        sh "set +x && oc login $hostURL --token=$token --insecure-skip-tls-verify && set -x"
    }
    sh "oc project $project"
}
