#!/usr/bin/groovy

/**
 * Pull the artifact from artifactory.
 * Assumption: Artifactory key credentials "artifactory_creds" is already set in Jenkins credentials.
 *
 * Example:
 * pullFromArtifactory(artifactoryURL: 'https://', artifactoryRepo: 'myrepo', artifactNameWithExt: 'xyz.jar')
 *
 * @param artifactoryURL
 * @param artifactoryRepo
 * @param artifactNameWithExt
 */
def call(args) {

	def artifactoryURL = args.artifactoryURL
	def artifactoryRepo = args.artifactoryRepo
	def artifactNameWithExt = args.artifactNameWithExt

	withCredentials([string(credentialsId: "artifactory_creds", variable: "key")]) {
		sh "curl -k -O -H \"X-JFrog-Art-Api:\${key}\" -X GET \"$artifactoryURL/$artifactoryRepo/$artifactNameWithExt ls -lrta .\""
	}
}
