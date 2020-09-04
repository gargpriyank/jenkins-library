#!/usr/bin/groovy

/**
 * Checkout code from github.
 *
 * Example:
 * gitCheckout(gitCreds: 'mygitcredsid', gitURL: 'https://', gitBranch: 'master')
 *
 * @param gitCreds
 * @param gitURL
 * @param gitBranch
 */
def call(args) {

    def gitCreds = args.checkCreds
    def gitURL = args.gitURL
    def gitBranch = args.gitBranch

    if (gitCreds) {
        git branch: gitBranch, credentialsId: gitCreds, url: gitURL
    } else {
        git branch: gitBranch, url: gitURL
    }
}
