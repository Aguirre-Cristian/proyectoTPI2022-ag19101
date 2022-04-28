pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'yes'
            }
        }
        stage('Test') {
            steps{
                sh "mvn clean compile test verify"

            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
