pipeline {
    agent any 
    environment {
         registry = "crisagui/baches:1.0"
         registryCredential = 'docker_hub'
         dockerImage = ''
    }
    
    stages {
    
    // Building Docker images
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry
        }
      }
    }
    // Uploading Docker images into Docker Hub
    stage('Upload Image') {
     steps{    
         script {
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
            }
        }
      }
    }

    // Running Docker container
    stage('Docker Run') {
     steps{
         script {
             dockerImage.run("docker run --docker run --rm -p 8080:8080 --add-host db:192.168.0.16 baches:2.0")
         }
      }
    }
  }
}