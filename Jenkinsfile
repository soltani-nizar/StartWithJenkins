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
       steps {
                 echo "nizar your project in tested"
                 }
		
}
     stage("SonarQube Analysis"){    
	 steps {
                 echo "nizar your project in tested"
                 }
	
}        	

}

}
