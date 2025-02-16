pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
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
                    junit '**/target/surefire-reports/*.xml' // Capture JUnit reports
                }
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn clean verify'  // Runs tests & generates coverage report
            }
            post {
                always {
                    jacoco execPattern: 'target/jacoco.exec'  // Ensures JaCoCo reports are generated
                }
            }
        }

        stage('Code Quality Check') {
            steps {
                withCredentials([string(credentialsId: 'new-sonar-token', variable: 'SONAR_LOGIN')]) {
                    bat 'mvn sonar:sonar -Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.login=%SONAR_LOGIN% -Dsonar.coverage.jacoco.xmlReportPaths=target/jacoco-report/jacoco.xml'
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
