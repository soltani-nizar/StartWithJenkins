pipeline {
    agent any

	 stages {
   
    stage("Build Application"){            
				steps {
        script{
        withMaven(maven : 'maven'){
          sh ' mvn clean compile -f pom.xml '
        }
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
