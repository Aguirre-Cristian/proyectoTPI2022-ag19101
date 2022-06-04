pipeline {
    agent any 
    

    environment {
        //una vez que se registre en Docker Hub, use ese ID de usuario aquí
        registry = "crisagui/baches:1.0"
        //actualice su ID de credenciales después de crear credenciales para conectarse a Docker Hub
        registryCredential = 'docker_hub'
        dockerImage = ''
    }
    
    stages {
    
    //  Construyendo imágenes de Docker 
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry
        }
      }
    }
    
     //Subir imágenes de Docker a Docker Hub 
    stage('Upload Image') {
     steps{    
         script {
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
            }
        }
      }
    }
    
     // Detención de contenedores Docker para una ejecución más limpia de Docker 
     stage('docker stop container') {
         steps {
            sh 'docker ps -f name=bachesCointainer -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=bachesCointainer -q | xargs -r docker container rm'
         }
       }
    
    
    // Ejecutando el contenedor Docker, asegúrese de que el puerto 8080 esté abierto en 
    stage('Docker Run') {
     steps{
         script {
            dockerImage.run("-p 8080:8080 --add-host db:192.168.0.16 --rm --name bachesCointainer")
         }
      }
    }
  }
}