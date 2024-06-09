pipeline {
    agent any
    environment {
        PATH = "/usr/local/bin:${env.PATH}"
    }
    options {
        timeout(time: 1, unit: 'HOURS')
        timestamps()
    }
    triggers {
        cron('H 4/* * * *')
    }
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branch to build')
    }
    stages {
        stage('Build') {
            steps {
                echo "Building branch ${params.BRANCH_NAME}"
                sh 'make build'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests'
                sh 'make test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                sh 'make deploy'
            }
        }
    }
    post {
        always {
            echo 'Cleaning up...'
            sh 'make clean'
        }
        success {
            echo 'Pipeline succeeded'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}
