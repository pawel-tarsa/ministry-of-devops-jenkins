// Declarative //
pipeline {
    agent { node 'slave01-ubuntu'}
	options {
		timestamps()
	}

    parameters {
        string (name: 'Branch', defaultValue:'develop', description: 'Branch that will be used with SCM')
        booleanParam (name: 'Deploy', defaultValue:'false', description: 'If you want to deploy on environment')
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
                stage('Branch A') {
                    steps {
                        echo "Testing on Branch A ..."
                        sleep 10
                    }
                }
                stage('Branch B') {
                    steps {
                        echo "Testing on Branch B ...."
                        sleep 15
                    }
                }
                stage ('Branch C') {
                    stages {
                        stage('Nested-1') {
                            steps {
                                echo "Nested-1"
                            }
                        }
                        stage('Nested-2') {
                            steps {
                                echo "Nested-2"
                            }
                        }

                    }
                }
        }
    }
}
}
// Script //