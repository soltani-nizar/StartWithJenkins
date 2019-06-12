pipeline {
    agent any
    stages {
   
    stage("Build Application and Upload to Nexus"){            
				steps {
              		    WithMaven(maven : 'maven'){
              		   sh'mvn clean compile'
              		    }
		               }
     }
			

     stage ('Run Unit Test ...') {
              steps {
                  WithMaven(maven : 'maven'){
              		    sh'mvn test'
                 }
     }
		
}
     stage("SonarQube Analysis"){    
			   steps {
                  WithMaven(maven : 'maven'){
              		    sh'mvn deploy'
                 }
      }                  
	
}        	

}

}
