// Declarative //
pipeline {
    agent none
	options {
		timestamps()
	}
    stages {
        stage('Pre-check') {
            steps {
                echo "ok"
                script {
                // def inputParams vs non def ->> Wyjasnic
                inputParams = input message: 'Continue?', parameters: [booleanParam(defaultValue: true, description: '', name: 'deploy'), booleanParam(defaultValue: true, description: '', name: 'test')], submitter: 'admin', submitterParameter: 'submitter'
                echo "${inputParams['deploy']}"
                echo "${inputParams['submitter']}"
                
            }
        }
        }
		stage ('Parallel') {
            parallel {
                stage('Pre-check') {
                    agent any
                    steps {
                        echo "Pre check.."
                        sleep 2
                    }
                }
                stage('Test') {
                    agent any
                    // Deploy when submitter admin and deploy is true
                    when {
                        expression {
                            return inputParams['deploy']
                        }
                    }
                    steps {
                        echo "Testing...."
                        sleep 2
                    }
                }
                stage ('Deploy') {
                    agent any
                    // Deploy when submitter admin and deploy is true
                    when {
                        expression {
                            return inputParams['deploy']&& inputParams['submitter']=="admin"
                        }
                    }
                    steps {
                        echo "Deploying..."
                        sleep 2
                    }
                }
        }
    }
}
}

// Script //