pipeline {
    agent any
    tools { 
        maven 'maven' 
        jdk 'jdk' 
    }
    stages {
        stage ('build project ...') {
            steps {
                 echo "nizar your project in building ...."
                 mvn -Dmaven.test.failure.ignore=true install
            }
        }

        stage ('Run Unit Test ...') {
            steps {
                 echo "nizar your project in tested"
            }
        }
          stage ('Run sonarQuality code....') {
            steps {
                 echo "nizar sonar is testing your code "
            }
        }
    }
}
