pipeline {

    agent any
    tools {
        maven 'maven' 
          jdk 'jdk' 
    }
    stages {
        stage('Compile stage') {
            steps {
                bat "mvn clean compile" 
        }
    }

         stage('testing stage') {
             steps {
                bat "mvn test"
        }
    }

          stage('deployment stage') {
              steps {
                bat "mvn clean deploy -Dmaven.test.skip=true"
        }
    }

  }

}
