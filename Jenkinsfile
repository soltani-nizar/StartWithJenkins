pipeline {
    agent any
    stages {
   
    stage("Build Application and Upload to Nexus"){            
				steps {
              		    WithMaven(maven : 'maven'){
              		    mvn clean compile
              		    }
		               }
     }
			

     stage ('Run Unit Test ...') {
              steps {
                  WithMaven(maven : 'maven'){
              		    mvn test
                 }
     }
		
}
     stage("SonarQube Analysis"){    
			   steps {
                  WithMaven(maven : 'maven'){
              		    mvn deploy
                 }
      }                  
	
}        	

}

}
