# Jenkins Library

Generic pipeline functions to be used in Jenkins pipelines. All the functions are available in form of groovy files in vars folder.

## How to use
 
1. Add this library in Jenkins client tool as a global library (**Manage Jenkins > Configure System > Global Pipeline Libraries**) and give it
a name, for example, **my-jenkins-library**.

2. Add the following at the top of the Jenkins pipeline.

```bash
@Library('my-jenkins-library') _
```

3. Call the Jenkins Library function from the Jenkins pipeline.

```bash 
stage('mystage') {
    steps {
        buildJarMaven(mvnArgs: '-DskipTests')
    }
}
```
