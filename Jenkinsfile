pipeline {
    agent any
    tools { 
        maven 'maven' 
        jdk 'jdk' 
    }
    stages {
        stage ('step 1 in pipeline') {
            steps {
                 echo "PATH = ${PATH}"
            }
        }

        stage ('step 2 in pipeline') {
            steps {
                echo 'This is a minimal pipeline.'
            }
        }
    }
}
