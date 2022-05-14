// Declarative //
def git_url = "https://gitlab.jenkins-training.eu/projekty/projekt-cwiczenie2.git/"

pipeline {
    agent any
    options {
        timestamps()
    }
    parameters {
        string(name: 'branch', defaultValue: 'master', description: 'branch to checkout')
    }
    stages {
        stage('Checkout') {
            steps {
                cleanWs()
                checkout([$class: 'GitSCM', branches: [[name: params.branch ]],extensions: [
        [$class: 'CloneOption', noTags: true, reference: '', shallow: true]
    ], userRemoteConfigs: [[credentialsId: 'jenkin-ci-push-gitlab', url: git_url]]])

                }
        }
        stage('Commit') {
            steps {
                sh "git checkout $branch"
                sh('echo \$BUILD_NUMBER > example-\$BUILD_NUMBER.md')
                sh 'git add . && git commit -am "[Jenkins CI] Add build file"'

            }
        }
        stage("Push") {
            steps {
            sh "git pull origin $branch"
            sh script:"git branch", returnStdout: true 
            withCredentials([usernamePassword(credentialsId: 'jenkin-ci-push-gitlab', usernameVariable: 'username', passwordVariable: 'password')])
            {
                sh "git push https://$username:$password@gitlab.jenkins-training.eu/projekty/projekt-cwiczenie2.git/"
            }
            }
        }
    }
}
    


