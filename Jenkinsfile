pipeline {
    agent any
    tools { 
        maven 'maven' 
        jdk 'jdk' 
    }
    stages {
   
    stage("Build Application and Upload to Nexus"){            
				steps {
              		    mvn -Dmaven.test.failure.ignore=true install
		               }
     }
			

     stage ('Run Unit Test ...') {
              steps {
                 echo "nizar your project in tested"
                 }
     }
		

     stage("SonarQube Analysis"){    
			    echo "nizar your project in tested"
      }                  
			
}        	

}
