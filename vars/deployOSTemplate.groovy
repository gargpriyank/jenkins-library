#!/usr/bin/groovy

/**
 * Build and deploy the OpenShift objects template using OpenShift CLI.
 *
 * Example:
 * deployOSTemplate(project: 'myproject', templateFullPath: 'template/sample-template.yaml', appName: 'myapp',
 * imageRegistryURL: 'docker.io', imageRepo: 'myrepo', memLimit: '512Mi', replicas: '2', imageTag: 'latest')
 *
 * @param project
 * @param templateFullPath
 * @param appName
 * @param imageRegistryURL
 * @param imageRepo
 * @param memLimit
 * @param replicas
 * @param imageTag
 */
def call(args) {

    def project = args.project
    def templateFullPath = args.templateFullPath
    def appName = args.appName
    def imageRegistryURL = args.imageRegistryURL
    def imageRepo = args.imageRepo
    def memLimit = args.memLimit ?: '512Mi'
    def replicas = args.replicas ?: '2'
    def imageTag = args.imageTag

    sh "oc project $project"
    sh "oc process -f $templateFullPath" +
            " -pNAME=$appName -pMEMORY_LIMIT=$memLimit -pREPLICAS=$replicas -pIMAGE_REGISTRY=$imageRegistryURL -pIMAGE_REPO=$imageRepo" +
            " -pIMAGE_TAG=$imageTag" +
            " | oc apply -f -"
}
