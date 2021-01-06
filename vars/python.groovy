def call(component) {

  DESCRIPTION = component.capitalize()

  pipeline {
    agent any

    environment {
      component = "${component}"
      DESCRIPTION = "${DESCRIPTION}"
    }

    stages {

      stage('Prepare Artifacts') {
        steps {
          sh '''
          mkdir -p publish
          cp -r *.py payment.ini requirements.txt publish
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