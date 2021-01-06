def call() {
  pipeline {
    agent any

    parameters {
      choice(name: 'ENV', choices: ['', 'dev', 'prod'], description: 'Pick Environment')
    }

    environment {
      AWS = credentials('AWS')
    }

    stages {

      stage('Terraform INIT') {
        steps {
          sh '''
          export AWS_ACCESS_KEY_ID=${AWS_USR}
          export AWS_SECRET_ACCESS_KEY=${AWS_PSW}
          make ${ENV}-init
        '''
        }
      }

      stage('Terraform APPLY') {
        steps {
          sh '''
          export AWS_ACCESS_KEY_ID=${AWS_USR}
          export AWS_SECRET_ACCESS_KEY=${AWS_PSW}
          make ${ENV}-apply
        '''
        }
      }

    }
  }

}
