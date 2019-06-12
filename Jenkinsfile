pipeline {
    agent any
	    tools { 
        maven 'maven' 
        jdk 'jdk' 
    }
    stages {
   
    stage("Build Application and Upload to Nexus"){            
				steps {
              		    WithMaven(maven : 'maven'){
              		   sh'mvn clean compile'
              		    }
		               }
     }
			

     stage ('Run Unit Test ...') {
       echo "nizar ok"
		
}
     stage("SonarQube Analysis"){    
	 echo "nizar ok 2"               
	
}        	

}

}
