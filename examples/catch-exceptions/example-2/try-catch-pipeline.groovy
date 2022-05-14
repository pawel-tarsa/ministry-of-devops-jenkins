pipeline {
    agent any
    stages {
        stage('1') {
            steps {
                sh 'exit 0'
            }
        }
        stage('2') {
            steps {
                script {
                    try {
                        sh 'exit 1'
                    }
                    catch (Exception e) {
                        println "exception ${e}"
                    }
                    finally {
                        sh 'exit 0'
                        currentBuild.result = 'UNSTABLE'
                        echo "RESULT: ${currentBuild.result}"
                    }
                }
                echo "RESULT: ${currentBuild.result}" 
                }
        }
        stage('3') {
            steps {
                sh 'exit 0'
            }
        }
    }
}


echo "RESULT: ${currentBuild.result}"