# Fitness Application 

## Aim of Project 

<p>Learn and implement key software architecture patterns</p>

## Background

<p>Fitness and well-being is not only a vital aspect of life, but has received unprecedented attention as an industry over the past decade. Individuals who offer services usually fall into one of two categories; a Personal Trainer who provides their services through employment at a Gym or any other fitness centre, or a Personal Trainer who provides their services as a sole proprietor in their individual business. The defining similarity between the two variants, is the lack of a tool to market, provide and monitor the services they offer to Clients. Currently, a Personal Trainer who is employed at a Gym would rely on the tools provided by the Gym to market their service, whilst communication with their Client is mainly through email. Through email, a Personal Trainer is able to provide customised workout plans for Clients based on their individual goals, whilst also utilising email as the main form of communication to receive constant updates from their Clients. The same procedure is utilised by the trainer who owns their business. </p>

## Project Outline

<p>The following system constitutes as an enterprise system that is a combination  of the following categories: Customer relationship management (CRM) and a Product data management (PRM). The software will provide login capabilities for two main users, Personal Trainers and Clients, where the application UI and functionality will change depending on which user is logged in. In the system, Client’s fall in either of two categories; a client who is being personally trained or a client who is receiving default fitness coaching through purchase of a fitness plan. </p>

## Architecture Patterns Used 

<p>
  <ul>
    <li>Service Layer Patterns</li>
    <ul>
    <li>Service Layer</li> 
     <li>Remote Facade</li> 
      <li>Data Transfer object</li> 
  	</ul>
   <li>Domain Layer Patterns</li>
    <ul>
    <li>Domain Model</li> 
  	</ul>
    <li>Data Source Layer Patterns</li>
     <ul>
    <li>Data Mapper</li> 
     <li>Implicit Lock</li> 
  	</ul>
     <li>Object-to-relational Behavioural Design Patterns</li> 
       <ul>
    <li>Unit of Work Pattern</li> 
     <li>Identity Map Pattern</li> 
     <li>Lazy Load Pattern</li> 
  	</ul>
    <li>Object-to-relational Structural Design Patterns</li> 
       <ul>
    <li>Identity Field Pattern</li> 
     <li>Foreign Key Mapping</li> 
     <li>Association Table Mapping</li> 
          <li>Embedded Value</li> 
             <li>Inheritance</li> 
  	</ul>
     <li>Security Patterns</li> 
      <ul>
    <li>Authentication Enforcer</li> 
     <li>Authorisation Enforcer</li> 
     <li>Intercepting Validator</li> 
          <li>Secure Pipe</li> 
         </ul>
    <li>Session Patterns</li> 
    <ul>
     <li>Server Session State</li> 
     </ul>
  	</ul>
  </ul> 
</p>

## Technology Used 

<ul>
  <li>Java Servlet</li>
  <li>JSP (Java Server Page)</li>
  <li>Apache Tomcat 9 </li>
  <li>Heroku</li>
</ul>
