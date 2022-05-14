 def git_url = "git@gitlab.jenkins-training.eu:projekty/skrypty-test.git"
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'gitlab', url: git_url]]])
            }
        }

        stage("Execute shell script with exit 1") {
            steps {
                script {
                    resultExecute = sh script:"./script-array.sh", returnStatus: true 
                    println resultExecute
                }
            }
        }
        stage("Check status of script") {
            steps {
                script {
                    if (resultExecute == 1 ) {
                        error("Throw exeception")
                    }
                    else if (resultExecute == 0 ) {
                        println "Status is: $resultExecute"
                    }
                }
            }
        }
    }
}