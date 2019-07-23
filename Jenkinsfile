pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /Users/cuiyishuo/.m2:/root/.m2'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }
    stage('Test') {
      post {
        always {
          echo 'finish'

        }

      }
      steps {
        sh 'mvn test'
      }
    }
    stage('Biuld Image') {
      steps {
        sh 'mv /compass-web/target/*.jar /docker/app.jar'
      }
    }
  }
}