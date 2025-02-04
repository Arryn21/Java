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
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
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
