#!/usr/bin/groovy

/**
 * Logout docker registry.
 *
 * Examples:
 * postScript(dockerRegistry: 'docker.io')
 *
 * @param dockerRegistry
 */
def call(args) {

    def dockerRegistry = args.dockerRegistry

    sh "docker logout $dockerRegistry"
}
