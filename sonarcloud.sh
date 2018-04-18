mvn sonar:sonar \
  -Dsonar.organization=rodrigogrohl-github \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=${SONARCLOUD_TOKEN}
  
