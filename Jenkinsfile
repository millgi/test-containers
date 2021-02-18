
pipeline {
  agent {
    label 'Build'
  }
  stages {
    stage('Build') {
      steps {
        container('java') {
          sh "./gradlew check --no-daemon --stacktrace"
        }
      }
      post {
        always {
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
        }
      }
    }
  }
}
