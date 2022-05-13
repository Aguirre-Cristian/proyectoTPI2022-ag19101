node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn= tool '3.8.5';
    withSonarQubeEnv() {
      sh "mvn clean verify sonar:sonar -Dsonar.projectKey=bacheBackend"
    }
  }
}
