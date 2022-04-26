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
                sh "mvn clean compile test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
