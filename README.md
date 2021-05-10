# DevOp Pipelines - Practical seven

## Introduction to Behaviour driven development (BDD) and testing with Cucumber

This is the starter repository for practical seven of the module **DevOps Pipelines**. The repository contains a simple IntelliJ Gradle project written in Java that demonstrates implementing a simple Gherkin scenario for adding money to a Revolut account. 

### Learning outcomes

After completing this practical exercise, you will be able to:  

- Configure an IntelliJ project to utilise Cucumber BBD framework.
- Document user stories as Cucumber scenarios using the Gherkin DSL.
- Implement definition steps and domain classes.
- Utilise a modern test framework (Junit / TestNG) to test a Cucumber scenario.
- Automatically build and test your Cucumber Java project using Cucumber, Gradle, (Junit or TestNG) and Github Actions.  

### Steps to complete

1. Fork this repository for the practical:  
2. Using Cucumber and Java, implement the additional scenarios documented in the TopUp.feature file.
3. Using Junit or TestNG test your implementation of the scenarios.
4. Create a repository on GitHub and upload your project to the repository.
5. Create a workflow file consisting of two jobs:  

    a. A Build job that builds your project using Gradle.
    b. A Test job that tests your project using your selected test framework and Gradle.  
