pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    triggers {
        pollSCM('H/2 * * * *')
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -B test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/quoteapp.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                bat 'deploy.bat'
            }
        }

        stage('Verify') {
            steps {
                sleep 5
                bat 'curl --fail http://localhost:9100/health'
            }
        }
    }

    post {
        success { echo 'Live at http://localhost:9100' }
        failure { echo 'Pipeline failed: check the red stage.' }
    }
}
