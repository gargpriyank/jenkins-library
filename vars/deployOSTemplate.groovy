#!/usr/bin/groovy

/**
 * Build and deploy the OpenShift objects template using OpenShift CLI.
 *
 * Example:
 * deployOSTemplate(project: 'myproject', templateFullPath: 'template/sample-template.yaml', appName: 'myapp',
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
 */
def call(args) {

    def project = args.project
    def templateFullPath = args.templateFullPath
    def appName = args.appName
    def dockerRegistry = args.dockerRegistry
    def dockerRepo = args.dockerRepo
    def memLimit = args.memLimit ?: '512Mi'
    def replicas = args.replicas ?: '2'
    def imageTag = args.imageTag

    sh "oc project $project"
    sh "oc process -f $templateFullPath" +
            " -pNAME=$appName -pMEMORY_LIMIT=$memLimit -pREPLICAS=$replicas -pIMAGE_REGISTRY=$dockerRegistry -pIMAGE_REPO=$dockerRepo" +
            " -pIMAGE_TAG=$imageTag" +
            " | oc apply -f -"
}
