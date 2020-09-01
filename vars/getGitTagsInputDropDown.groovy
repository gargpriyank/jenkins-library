#!/usr/bin/groovy

/**
 * Display dropdown with all git tags corresponding to the application.
 * Assumption: Github account credentials "github_creds" is already set in Jenkins credentials.
 *
 * Example:
 * getGitTagsInputDropDown(appName: 'myapp', gitOrg: 'myorg')
 *
 * @param appName
 * @param gitOrg
 * @return inputDropDown
 */
def call(args) {

    def appName = args.appName
    def gitOrg = args.gitOrg
    def gitTags = getGitTags(appName: "$appName", gitOrg: "$gitOrg")
    def inputDropDown = input message: 'Please select a Github/Docker Tag to deploy', ok: 'Next', parameters: [choice(name: 'imageVersion', choices: "$gitTags", description: 'Available Github/Docker Tags')]
    return inputDropDown
}
