pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_LOGIN = 'sonarqube-token'
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
            post {
                always {
                    junit '**/target/surefire-reports/*.xml' // Ensure JUnit test reports are captured
                }
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn clean verify'  // Runs tests & generates coverage report
            }
            post {
                always {
                    jacoco execPattern: 'target/jacoco.exec'  // Ensure correct coverage file
                }
            }
        }

        stage('Code Quality Check') {
            steps {
                bat 'mvn sonar:sonar -Dsonar.host.url=${SONAR_HOST_URL} -Dsonar.login=${SONAR_LOGIN} -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'
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
