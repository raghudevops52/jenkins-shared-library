def call(component) {

  DESCRIPTION = component.capitalize()

  pipeline {
    agent any

    tools {
      maven 'MAVEN'
      //jdk 'JDK'
    }

    environment {
      component = "${component}"
      DESCRIPTION = "${DESCRIPTION}"
    }

    stages {

      stage('Prepare Artifacts') {
        steps {
          sh '''
          mkdir -p publish
          mvn package
          cp -r target/${component}-1.0.jar publish/${component}.jar 
        '''
        }
      }

      stage('Publish Artifacts') {
        steps {
          sh '''
          cd publish
          az artifacts universal publish --organization https://dev.azure.com/DevOps-Batches/ --project="DevOps52" --scope project --feed devopsb52 --name ${component} --description "${DESCRIPTION}" --version 0.0.${BUILD_NUMBER} --path .
        '''
        }
      }

    }
  }

}