pipeline {
    agent none
    stages {
        stage('Build') {
            agent{
                docker {
                    image 'maven:3.6.3-jdk-11'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests -B -ntp'
            }
        }
        stage('E2E Testing') {
            agent{
                docker {
                    image 'amazon/aws-cli'
                    args '--entrypoint=""'
                }
            }
            steps {
                sh 'aws-cli --version'
            }
        }
    }
}