node {

    def project_dir = 'testing'

    stage('Checkout') {
        dir(project_dir) {
            git branch: 'master', url: 'https://github.com/astrozzc/jenkins-example.git'
        }
    }

    def tagging = stage('CheckTag') {
        return sh(script: "git --git-dir=${project_dir}/.git tag --points-at HEAD", returnStdout: true)
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