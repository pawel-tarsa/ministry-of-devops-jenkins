pipeline {
    agent any
    stages {
        stage('First stage') {
            steps {
                sh 'exit 0'
            }
        }
        stage('Second stage') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                    sh "exit 1"
                }
            }
        }
        stage('Third stage') {
            steps {
                sh 'exit 0'
            }
        }
    }
}