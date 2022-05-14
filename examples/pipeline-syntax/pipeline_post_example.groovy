// Declarative //
def git_url = "https://github.com/jenkins-docs/simple-java-maven-app"
def branch  = "*/master"

pipeline {
    agent {
        node 'slave01-ubuntu'
    }
    options {
        timestamps()
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: branch ]], userRemoteConfigs: [[url: git_url]]])
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
        }
    }
}

