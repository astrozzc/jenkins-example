node {

    def project_dir = 'testing'
    def bop_branch = 'master'
    def bop_dir = 'bop_repo'

    stage('Checkout') {
        dir(project_dir) {
            git branch: 'master', url: 'https://github.com/astrozzc/jenkins-example.git'
        }
    }

    def tagging = stage('CheckTag') {
        dir(bop_dir) {
            git branch: bop_branch, url: 'https://github.com/astrozzc/jenkins-example.git'
        }
        def gitTag = sh(script: "git --git-dir=${bop_dir}/.git tag --points-at HEAD", returnStdout: true)
        return "${gitTag}${env.BUILD_NUMBER}"
    }

    if (!tagging) {
        error 'Please add tag to the HEAD of your repo!'
    }

    def image = "images.paas.redhat.com/insights-services/backoffice-proxy:${tagging}"

    sh "echo ${image}"

    stage('checking') {
        sh "echo 'still here!!!!!!!!'"
    }
}