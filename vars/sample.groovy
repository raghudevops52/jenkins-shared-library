def error(message) {
  echo "ERROR: ${message}"
}

def call(component, DESCRIPTION) {
    pipeline {
      agent any

      environment {
        COMPONENT = "${component}"
      }
      stages {
        stage('Sample') {
          steps {
            script {
              error("This is sample from Shared Library")
            }
            print "${component}"

            sh '''
              echo ${COMPONENT}
            '''
          }
        }
      }
    }
}
