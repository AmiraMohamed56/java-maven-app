def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh ' docker build -t haytham1992/java-maven-app:jma-1.0 . .'
        sh "echo $PASS | docker login -u $USER -p $PASSWORD"
        sh 'docker push haytham1992/java-maven-app:jma-1.0
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
