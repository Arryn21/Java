pipeline {
    agent any

    environment {
        SONARQUBE = 'SonarQube'  // Ensure this matches the configured SonarQube server name in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Arryn21/Java.git'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv(SONARQUBE) {
                        'mvn clean verify sonar:sonar -Dsonar.projectKey=JenkinsIntegration -Dsonar.projectName=JenkinsIntegration -Dsonar.projectVersion=1.0 -Dsonar.sources=src/main/java -Dsonar.tests=src/test/java'
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 5, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    'mvn test'
                }
            }
        }
    }

    post {
        success {
            echo 'Build and tests were successful!'
        }
        failure {
            echo 'Something went wrong. Check logs!'
        }
    }
}
