#!/usr/bin/groovy

/**
 * Build and deploy the OpenShift objects template using OpenShift CLI.
 *
 * Example:
 * processOSTemplate(project: 'myproject', templateFullPath: 'template/sample-template.yaml', appName: 'myapp',
 * dockerRegistry: 'docker.io', dockerRepo: 'myrepo', memLimit: '512Mi', replicas: '2', imageTag: 'latest')
 *
 * @param project
 * @param templateFullPath
 * @param appName
 * @param dockerRegistry
 * @param dockerRepo
 * @param memLimit
 * @param replicas
 * @param imageTag
 * @param githubURL
 * @param githubBranch
 */
def call(args) {

    def project = args.project
    def templateFullPath = args.templateFullPath
    def appName = args.appName
    def dockerRegistry = args.dockerRegistry
    def dockerRepo = args.dockerRepo
    def memLimit = args.memLimit
    def replicas = args.replicas
    def imageTag = args.imageTag
    def githubURL = args.githubURL
    def githubBranch = args.githubBranch
    def stmt = "oc process -f $templateFullPath"

    if (appName != null) {
        stmt += " -pNAME=$appName"
    }
    if (memLimit != null) {
        stmt += " -pMEMORY_LIMIT=$memLimit"
    }
    if (replicas != null) {
        stmt += " -pREPLICAS=$replicas"
    }
    if (dockerRegistry != null) {
        stmt += " -pIMAGE_REGISTRY=$dockerRegistry"
    }
    if (dockerRepo != null) {
        stmt += " -pIMAGE_REPO=$dockerRepo"
    }
    if (imageTag != null) {
        stmt += " -pIMAGE_TAG=$imageTag"
    }
    if (githubURL != null) {
        stmt += " -pGITHUB_URL=$githubURL"
    }
    if (githubBranch != null) {
        stmt += " -pGITHUB_BRANCH=$githubBranch"
    }

    sh "oc project $project"
    sh "$stmt | oc apply -f -"
}
