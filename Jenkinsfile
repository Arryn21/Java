pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Arryn21/Java.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Use bat to run Maven on Windows
                    'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Use bat to run Maven test command
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
