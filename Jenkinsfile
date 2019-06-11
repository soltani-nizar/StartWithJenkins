pipeline {
    agent any
    tools { 
        maven 'maven' 
        jdk 'jdk' 
    }
    stages {
    stage("Build Application and Upload to Nexus"){            
				steps {
                 
		     			mvn clean package -P FullStack -f pom.xml
						
						cd back-end-java/
						zip -9 -r ../infrastructure.zip infrastructure/*
					                    }
                }
			}

        stage ('Run Unit Test ...') {
            steps {
                 echo "nizar your project in tested"
            }
        }
		stage("SonarQube Analysis"){           
                steps {
                    script {
                        if ("${GIT_BRANCH}"=="master")
						{echo "=== Scanning BackEnd project with SonarQube ==="
						{sh 'mvn sonar:sonar -Dsonar.projectKey=fr.sparkit.sparkys.javaangular.back'}
					 	sleep 30
                        }
						
						if ("${GIT_BRANCH}"!="master") {echo "SonarQube Analysis skipped beacause the current branch is not the master"}
                    }
                }                  
			}
            	


    }
}
