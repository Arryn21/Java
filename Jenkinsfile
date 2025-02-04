pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_LOGIN = 'sqp_ff90d30792667ff1b6b4ffa05e481bbffb2db0dd'
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
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Quality Check') {
            steps {
              bat """
            mvn sonar:sonar \
                -Dsonar.projectKey=JenkinsIntegration \
                -Dsonar.projectName=JenkinsIntegration \
                -Dsonar.projectVersion=1.0 \
                -Dsonar.sources=src/main/java \
                -Dsonar.tests=src/test/java \
                -Dsonar.host.url=$SONAR_HOST_URL \
                -Dsonar.login=$SONAR_LOGIN
        """
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
