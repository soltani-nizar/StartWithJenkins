pipeline {
    agent any
	 stages {
   
    stage("Build Application"){            
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
