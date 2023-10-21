
  folder("/${project["name"]}")

  multibranchPipelineJob("/${project["name"]}/${repo["name"]}") {
    description("${repo["name"]}")
    branchSources {
      branchSource {
        source {
          github {
              id("${project["name"]}")
              configuredByUrl(true)
              repoOwner("aishroom")
              repository("${repo["name"]}")
              credentialsId('github-jenkins-unicauca')
              repositoryUrl("https://github.com/aishroom/${repo["name"]}")
              traits {
                  gitHubBranchDiscovery {
                      strategyId(3)
                  }
              }
          }   
        }
        strategy {}
      }
    }
    orphanedItemStrategy {
      discardOldItems {}
    }
    factory {
      remoteJenkinsFileWorkflowBranchProjectFactory {
        remoteJenkinsFile("${repo["pipeline"]}")
        localMarker('Jenkinsfile.json')
        remoteJenkinsFileSCM {
          gitSCM {
            userRemoteConfigs    {
              userRemoteConfig {
                name('')
                url('https://github.com/aishroom/pipelines')
                refspec('')
                credentialsId('github-jenkins-unicauca')
              }
            }
            branches {
              branchSpec {
                name('master')
              }
            }
            browser {} // required, but doesn't require configuration
            gitTool('/usr/bin/env git') // or wherever makes sense
          }
        }
        matchBranches(false)
        fallbackBranch('master')
        lookupInParameters(false)
      }
    }
  }
    
  