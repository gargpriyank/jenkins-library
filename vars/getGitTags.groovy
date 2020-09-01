#!/usr/bin/groovy

/**
 * Return all git tags corresponding to the application.
 * Assumption: Github account credentials "github_creds" is already set in Jenkins credentials.
 *
 * Example:
 * getGitTags.groovy(appName: 'myapp', gitOrg: 'myorg')
 *
 * @param gitOrg
 * @param appName
 * @return updatedTagsList
 */
def call(args) {

    def gitOrg = args.gitOrg
    def appName = args.appName
    def updatedTagsList = []

    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'github_creds', usernameVariable: 'userName', passwordVariable: 'password']]) {
        sh "git remote set-url origin https://$userName:$password@github.com/$gitOrg/${appName}.git"
        def tagsTxt = sh(script: "git ls-remote --tags |  cut -d '/' -f 3 | sort -V -r", returnStdout: true)
        def tagsList = tagsTxt.split()
        updatedTagsList = tagsList.join("\n")
    }
    return updatedTagsList
}
