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
<<<<<<< HEAD
      stage('Move jar') {
                  steps {
                      sh 'mv /compass'
                  }
                  post {
                      always {
                          echo "finish"
                      }
                  }
            }
   }
=======
    }
    stage('Test') {
      post {
        always {
          echo 'finish'

        }
>>>>>>> 0de316afa06d88f17d1498c4cd2b3621da24788f

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