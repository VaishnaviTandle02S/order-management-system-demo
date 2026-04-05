pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run') {
            steps {
                sh 'java -jar target/*.jar'
            }
        }
    }
}