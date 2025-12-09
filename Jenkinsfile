pipeline {
    agent any
    tools {
        maven 'M399' // Ensure Maven is configured in Jenkins
    }
    environment {
        IMAGE_NAME = 'AmiraaMohamed/java-maven-app2'
        IMAGE_TAG = "jma02.12.${env.BUILD_NUMBER}" // Dynamic tag using build number
        DOCKER_HUB_CREDENTIALS = 'docker-hub' // Credentials ID for Docker Hub
    }
    stages {
        stage('Build JAR') {
            steps {
                script {
                    echo "Building the application..."
                    sh 'mvn clean package'
                }
            }
        }
        stage('Build and Push Docker Image') {
            steps {
                script {
                    echo "Building the Docker image..."
                    withCredentials([usernamePassword(credentialsId: 'env.docker-hub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh """
                        docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                        echo "${PASSWORD}" | docker login -u "${USERNAME}" --password-stdin
                        docker push ${IMAGE_NAME}:${IMAGE_TAG}
                        """
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo "Deploying the application..."
                    // Add deployment commands here
                    // Example: Deploy to Kubernetes or a server
                    // sh 'kubectl apply -f deployment.yaml'
                }
            }
        }
    }
    post {
        success {
            echo "Pipeline succeeded!"
            // Add notifications (e.g., email, Slack)
        }
        failure {
            echo "Pipeline failed!"
            // Add notifications (e.g., email, Slack)
        }
        cleanup {
            echo "Cleaning up workspace..."
            // Clean up resources if needed
        }
    }
}
