pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_LOGIN = credentials('sonarqube-token')
    }
    
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Arryn21/Java.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Unit Test') {
            steps {
                bat 'mvn test'
            }
            //post {
                //always {
               //     junit '**/target/surefire-reports/*.xml'
              //  }
        //    }
        }

        stage('Code Quality Check') {
            steps {
                bat 'mvn sonar:sonar -Dsonar.token=sqa_a9e3cf576b597b2e30fb14695786e0e068f3c269'
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn clean verify'
            }
            post {
                always {
                    jacoco execPattern: 'target/jacoco.exec'
                }
            }
        }

        stage('Artifact Generation') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Notification') {
            steps {
                mail to: 'tigertharu21@gmail.com',
                     subject: "Build ${currentBuild.result}: Job ${env.JOB_NAME}",
                     body: "Build ${env.BUILD_NUMBER} completed.\nCheck details: ${env.BUILD_URL}"
            }
        }
    }

    post {
        success {
            echo 'Build and tests passed successfully!'
        }
        failure {
            echo 'Build failed. Check logs for details.'
        }
    }
}
