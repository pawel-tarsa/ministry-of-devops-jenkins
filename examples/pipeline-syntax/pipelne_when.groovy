// Declarative //
pipeline {
    agent any
	options {
		timestamps()
	}
     parameters {
        choice (name: 'Environment', choices:['dev','stage','prod'], description: 'Environment name for deployment')
    }
    stages {
        stage('Deploy dev') {
            when {
                environment name: 'Environment', value: 'dev'
            }
            steps { 
                echo "Deploying dev"
            }
        }
        stage('Deploy stage') {
            when {
                environment name: 'Environment', value: 'stage'
            }
            steps { echo "Deploying dev"
            }
        }
        stage('Deploy prod') {
            when {
                environment name: 'Environment', value: 'prod'
            }
            steps {
                echo "Deploying prod"
            }
        }
    }
}
// Script //