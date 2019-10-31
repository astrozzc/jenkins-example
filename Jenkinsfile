pipeline {
    agent any

    stage('Login as deployer account') {
        withCredentials([string(credentialsId: 'HccmRbacDevDeployerToken', variable: 'TOKEN')]) {
            sh "oc login https://api.insights-dev.openshift.com --token=${TOKEN}"
        }

        sh "oc project rbac-ci"
    }

    stage('Create ConfigMap') {
        sh "oc create configmap test-config --from-file=."
    }
}