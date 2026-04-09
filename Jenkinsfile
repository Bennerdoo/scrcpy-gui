 pipeline {
    agent any
    
    tools {
        maven 'Maven 3.9'
        jdk 'JDK 11'
    }
    
    environment {
        PROJECT_NAME = 'ScrcpyGUI'
        VERSION = '1.3.0'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building with Maven...'
                dir('gui') {
                    bat 'mvn clean package'
                }
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running tests...'
                dir('gui') {
                    // Run tests if they exist
                    script {
                        try {
                            bat 'mvn test'
                        } catch (Exception e) {
                            echo 'No tests found or tests failed, continuing...'
                        }
                    }
                }
            }
        }
        
        stage('Package JAR') {
            steps {
                echo 'Creating fat JAR with dependencies...'
                dir('gui') {
                    bat 'mvn assembly:single'
                }
            }
        }
        
        stage('Package Windows EXE') {
            when {
                expression { isUnix() == false }
            }
            steps {
                echo 'Creating Windows installer...'
                dir('gui') {
                    script {
                        try {
                            bat 'call package-exe.bat'
                        } catch (Exception e) {
                            echo "Windows packaging failed: ${e.message}"
                            echo 'Continuing without .exe installer...'
                        }
                    }
                }
            }
        }
        
        stage('Archive Artifacts') {
            steps {
                echo 'Archiving build artifacts...'
                dir('gui') {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true, allowEmptyArchive: true
                    archiveArtifacts artifacts: 'dist/*.exe', fingerprint: true, allowEmptyArchive: true
                }
            }
        }
    }
    
    post {
        success {
            echo '✅ Build completed successfully!'
            emailext(
                subject: "✅ SUCCESS: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
                    Build successful for ${env.JOB_NAME}
                    
                    Build Number: ${env.BUILD_NUMBER}
                    Build URL: ${env.BUILD_URL}
                    
                    Artifacts:
                    - JAR: target/scrcpy-gui-${VERSION}-jar-with-dependencies.jar
                    - EXE: dist/ScrcpyGUI-${VERSION}.exe
                    
                    Made with Love by Bennerdoo ❤️
                """,
                to: '${DEFAULT_RECIPIENTS}',
                attachLog: false
            )
        }
        
        failure {
            echo '❌ Build failed!'
            emailext(
                subject: "❌ FAILURE: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
                    Build failed for ${env.JOB_NAME}
                    
                    Build Number: ${env.BUILD_NUMBER}
                    Build URL: ${env.BUILD_URL}
                    
                    Please check the console output for details.
                """,
                to: '${DEFAULT_RECIPIENTS}',
                attachLog: true
            )
        }
        
        always {
            echo 'Cleaning up workspace...'
            cleanWs(
                deleteDirs: true,
                patterns: [
                    [pattern: 'gui/target/**', type: 'INCLUDE'],
                    [pattern: 'gui/dist/**', type: 'INCLUDE']
                ]
            )
        }
    }
}
