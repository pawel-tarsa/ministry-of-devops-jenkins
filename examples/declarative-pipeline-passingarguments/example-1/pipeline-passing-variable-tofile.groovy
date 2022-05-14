def git_url = "git@gitlab.jenkins-training.eu:projekty/skrypty-test.git"
pipeline {
    agent {
        node "slave01-ubuntu"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'gitlab', url: git_url]]])            }
            }
        stage("Get list of IPs string script") {
            steps {
                script {
                    componentString = sh script:"./script-string.sh", returnStdout: true 
                    println componentString
                    println componentString.getClass()
                }
            }
        }   
        stage("Pass list to file 1 - shell") {
            steps {
                sh "./script-echo.sh $componentString"
                sh "cat output.txt"
                input "Should we continue ?"
            }
        }
        stage("Get list of IPs array script") {
            steps {
                script {
                    componentArray = sh script:"./script-array.sh", returnStdout: true 
                    println componentArray
                    println componentArray.getClass()
                }
            }
        }
        stage("Pass list to file 2 - shell") {
            steps {
                sh "./script-echo.sh $componentArray"
                sh "cat output.txt"
                input "Should we continue ?"
            }
        }
        stage("Pass list to file 3 -  powershell ") {
            agent {
                node "slave01-windows"
            }
            steps {
                powershell "./script-array.ps1 $componentString"
                input "Should we continue ?"
            }
        }

        stage("Convert list of IPs to groovy array") {
            steps {
                script {
                    componentString2 = sh script:"./script-string.sh", returnStdout: true 
                    groovyArray = componentString2.tokenize(',')
                    println groovyArray.getClass()
                }
            }
        }
        stage("Pass list to file 3") {
            steps {
                sh "./script-echo.sh \"$groovyArray\""
                sh "cat output.txt"
            }
        }
    }
}