node {
  def mvnHome
  
  stage('Preparation') {
    git 'https://github.com/LinuxSuRen/phoenix.webui.framework.git'
    mvnHome = tool 'M3'
  }
  
  stage('Clean') {
    if(isUnix()){
      sh "'${mvnHome}/bin/mvn' clean"
    }else{
      bat(/"${mvnHome}\bin\mvn" clean/)
    }
  }
  
  stage('Test') {
    if(isUnix()){
      sh "'${mvnHome}/bin/mvn' test"
    }else{
      bat(/"${mvnHome}\bin\mvn" test/)
    }
  }
  
  stage('JavaDoc') {
    if(isUnix()){
      sh "'${mvnHome}/bin/mvn' javadoc:jar -DdocSkip=false"
    }else{
      bat(/"${mvnHome}\bin\mvn" javadoc:jar -DdocSkip=false/)
    }
  }
  
  stage('Site') {
    if(isUnix()){
      sh "'${mvnHome}/bin/mvn' site"
    }else{
      bat(/"${mvnHome}\bin\mvn" site/)
    }
  }
  
  stage('Package') {
    if(isUnix()){
      sh "'${mvnHome}/bin/mvn' --update-snapshots package"
    }else{
      bat(/"${mvnHome}\bin\mvn" --update-snapshots package/)
    }
  }
  
  stage('Archive Site') {
    sh "tar -czvf target/site.tar.gz -C target site"
    archiveArtifacts 'target/site.tar.gz'
  }
  
  stage('Deploy') {
    if(isUnix()){
      sh "'${mvnHome}/bin/mvn' deploy -DsignSkip=false -DdocSkip=false"
    }else{
      bat(/"${mvnHome}\bin\mvn" deploy -DsignSkip=false -DdocSkip=false/)
    }
  }
}

properties([
    [
        $class: 'GithubProjectProperty',
        displayName: 'phoenix.webui.framework',
        projectUrlStr: 'https://github.com/LinuxSuRen/phoenix.webui.framework'
    ],
    buildDiscarder(
        logRotator(
            artifactDaysToKeepStr: '',
            artifactNumToKeepStr: '',
            daysToKeepStr: '7',
            numToKeepStr: '14'
        )
    ),
    pipelineTriggers([
        pollSCM('H/15 * * * *')
    ])
])