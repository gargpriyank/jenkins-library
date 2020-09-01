#!/usr/bin/groovy

/**
 * Delete all OpenShift objects associated to app name.
 *
 * Examples:
 * cleanUpAllOSObjects(appName: 'myapp')
 *
 * @param appName
 */
def call(appName) {
    sh "oc delete all -l app=$appName || true"
}
