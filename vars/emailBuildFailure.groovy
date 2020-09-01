#!/usr/bin/groovy

/**
 * Send an email for a build failure to provided recipients list.
 *
 * Examples:
 * emailBuildFailure(appName: 'myapp', deployEnv: 'development', buildNumber: '1', recipients: 'xyz@abc.com')
 *
 * @param appName
 * @param deployEnv
 * @param buildNumber
 * @param recipients
 */
def call(args) {

    def appName = args.appName
    def deployEnv = args.deployEnv
    def buildNumber = args.buildNumber
    def recipients = args.recipients

    emailext body: "Please fix the $appName application $deployEnv pipeline with build# $buildNumber ran into issues.", subject: "Build# " +
            "$buildNumber failed for $appName application in $deployEnv pipeline.", to: "$recipients"
}
