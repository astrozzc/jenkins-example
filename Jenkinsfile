pipeline {
    agent any

    stages {
        stage('Login as deployer account') {
            steps {
                withCredentials([string(credentialsId: 'HccmRbacDevDeployerToken', variable: 'TOKEN')]) {
                    sh "oc login https://api.insights-dev.openshift.com --token=${TOKEN}"
                }

                echo GIT_BRANCH
                sh "oc project rbac-ci"
            }
        }   

        stage('Create ConfigMap') {
            steps {
                sh "oc create configmap test-config --from-file=configs --dry-run -o json | oc apply -f -"
            }
        }   
    }

}