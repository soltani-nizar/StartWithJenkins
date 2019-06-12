pipeline {
    agent any
	   tools { 
        maven 'maven' 
        jdk 'jdk' 
    }
	 stages {
   
    stage("Build Application"){            
				steps {
              		    
              		   sh ' mvn clean compile -f pom.xml '
              		  
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
