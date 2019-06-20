pipeline {

    agent any
    tools {
        maven 'maven' 
          jdk 'jdk' 
    }
    stages {
        stage('Compile stage') {
            steps {
                bat "mvn clean install -P FullStack" 
        }
    }
 stage('sonar scanner stage') {
             steps {
                bat "mvn sonar:sonar"
        }
    }
      
         stage('docker build stage') {
             steps {
                bat 'docker build --tag="cont1:1.0.0" -f ./infrastructure/Dockerfile'
                bat "docker run -d --name CRM1 cont1:1.0.0"
        }
    }

  }

}
