
node {
stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn= tool '3.8.5';
    withSonarQubeEnv() {
      sh "mvn clean verify sonar:sonar -Dsonar.projectKey=bacheBackend"
    }
  
    
    environment {
        //una vez que se registre en Docker Hub, use ese ID de usuario aquí 
        registry = "crisagui/baches1.0"
        //- actualice su ID de credenciales después de crear credenciales para conectarse a Docker Hub 
        registryCredential = 'ad2a8cd6-c546-4afc-9740-566faa48c840'
        dockerImage = ''
    }
    
      stages {
        stage('Cloning Git') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/Aguirre-Cristian/proyectoTPI2022-ag19101.git']]])       
            }
        }
    
    // Construyendo imágenes de Docker
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry
        }
      }
    }
    
     // Subir imágenes de Docker a Docker Hub
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
            sh 'docker ps -f name=mypythonappContainer -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=mypythonappContainer -q | xargs -r docker container rm'
         }
       }
    
    
    // Ejecutando el contenedor Docker, asegúrese de que el puerto 8096 esté abierto en 
    stage('Docker Run') {
     steps{
         script {
            dockerImage.run("-p 8080:8080 --rm --name baches1.0")
         }
      }
    }
  }
}
}
