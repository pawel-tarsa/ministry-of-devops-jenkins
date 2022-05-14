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
                try {
                // def inputParams= vs inputParams= ->> Wyjasnic
                inputParams = input message: 'Continue?', parameters: [booleanParam(defaultValue: true, description: '', name: 'deploy'), booleanParam(defaultValue: true, description: '', name: 'test')], submitter: 'admin', submitterParameter: 'submitter' 
                }
                catch(Exception e) {
                    inputParams = false
                }
                
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
                        not {
                        expression {
                            return inputParams
                        }
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