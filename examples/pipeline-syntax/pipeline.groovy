// Declarative //
pipeline {
    agent { node 'slave01-ubuntu'}
	options {
		timestamps()
	}
    stages {
        stage('Build') {
            steps {
                sleep 10
                echo "OK"
            }
        }
		stage ('Testing') {
            failFast true
            parallel {
                stage('Test on Linux') {
                    steps {
                        echo "Testing on Linux ..."
                        sleep 10
                    }
                }
                stage('Test on Windows') {
                    steps {
                        echo "Testing on Windows ...."
                        sleep 15
                    }
            }
        }
    }
}
}
// Script //