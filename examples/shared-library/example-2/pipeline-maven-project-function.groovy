@Library('my-shared-library@master') _

buildMaven(
    node: "slave01-ubuntu",
    gitUrl:"https://github.com/jenkins-docs/simple-java-maven-app.git",
    branch: "master",
    deliverScriptPath: "./jenkins/scripts/deliver.sh" )