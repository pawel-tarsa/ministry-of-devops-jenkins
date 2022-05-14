def git_url = "git@gitlab.jenkins-training.eu:projekty/skrypty-test.git"
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'gitlab', url: git_url]]])
            }
        }

        stage("Read json") {
            steps {
                script {
                    def props = readJSON file: 'example.json'
                    println props
                    println props['quiz']['sport']['q1']['options']
                }
                

            }
        }
    }
}