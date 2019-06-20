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
                bat "sonar-scanner"
        }
    }
       

      

  }

}
