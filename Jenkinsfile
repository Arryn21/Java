pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
    }

    environment {
        SONARQUBE = 'SonarQube'
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
                    // SonarQube Analysis
                    withSonarQubeEnv(SONARQUBE) {
                        'mvn sonar:sonar'
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
