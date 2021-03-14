## CRUD Application Project

This is the README for my Dream Journal, a CRUD application incorporating back-end development in the Spring Boot framework, front-end development using HTML, CSS and Javascript and database managment using SQL.

### Contents
1. External links
2. Brief & planning
3. Jira board
4. GitHub repositories
5. Tech stack
6. Testing
7. Front-end
8. Stretch goals

### External Links
* Presentation [here](https://docs.google.com/presentation/d/1b0qFawZiUV-c0SG7eYzooVYo84VoeGZXF3bsFy_7LP0/edit?usp=sharing)
* Jira board [here](https://team-1612863346985.atlassian.net/jira/software/c/projects/DREAM/issues/)
* Back-end GitHub repository [here](https://github.com/imogen881/DreamJournal)
* Front-end GitHub repository [here](https://github.com/imogen881/DreamJournalFrontEnd)

### Brief & Planning Phase
The brief: "To create a CRUD application with the utilisation of supporting tools, methodoligies and technologies"

To meet the brief I have created a dream journal application where users can **create** new dream entries, **read** existing entries, **update** existing entries and **delete** any existing entries. The application aims to allow users to record their dreams as a tracker of their state of mind and to spot any patterns in their dreaming.

### Jira Board
Jira board accessible [here](https://team-1612863346985.atlassian.net/jira/software/c/projects/DREAM/issues/)

The Kanban Board was produced on Jira using Scrum methodology. I structured my work in to two sprints - one for the back-end and one for the front-end.

User stories based on the CRUD functionality made up my backlog, under which I subdivided them in to tasks. I assigned the tasks story points and priority rankings.

My back-end sprint kanban board:

![kanbanboardOne](https://github.com/imogen881/DreamJournal/blob/main/ReadmeResources/JiraTwo.PNG)

Example of user story:

![userStory](https://github.com/imogen881/DreamJournal/blob/main/ReadmeResources/JiraSeven.PNG)

### Github repositories

Git was my version control system with remote repositories stored on Github. I had a separate repository for the back-end and front-end, both following the feature branch model.

Back-end repository network graph:

![backEndNetworkGraph](https://github.com/imogen881/DreamJournal/blob/main/ReadmeResources/GitOne.PNG)

Front-end repository network graph:

![backEndNetworkGraph](https://github.com/imogen881/DreamJournal/blob/main/ReadmeResources/GitTwo.PNG)

### Tech Stack

#### Front-end

The front-end was developed using HTML, CSS and Javascript. Bootstrap was used to add styling and to utilise their grid component for layout. Javascript is used to send requests and recieve responses from the back-end using the Axios library.

#### Back-end

The back-end is written in Java using the Spring Boot Framework. It includes the three major components of a REST controller, a service class and a database interface. The back-end connects the front-end to the database and handles the business logic.

#### Database

The back-end can connect to a H2 database for testing or to a SQL database for persistence.

The Entity Relationship Diagram (ERD) for the SQL database:

![schema](https://github.com/imogen881/DreamJournal/blob/main/ReadmeResources/SQLSchema.PNG)

### Testing

Integration tests for the back-end were written using MockMVC to create mock HTTP requests to test the four key CRUD functionalities and how they interacted. The H2 database was used for testing and was cleared and then given one default entry before each test to ensure a standardised starting point.

Back-end unit tests were written using JUnit and Mockito to check whether the methods themselves were working on an individual level.

Selenium was used for front-end integration tests and tested the create, update and delete methods.

The coverage for my controller and service classes were both above 80%:

![testCoverage](https://github.com/imogen881/DreamJournal/blob/main/ReadmeResources/TestCoverage.PNG)

### Front-End

The front-end design aimed to be light through the use of blue and white and the cloud imagery. It is a simple design featuring the table of dream entries, with all the functionality accessible on one page.

![frontEnd](https://github.com/imogen881/DreamJournal/blob/main/ReadmeResources/FEOne.PNG)

![frontEndTwo](https://github.com/imogen881/DreamJournal/blob/main/ReadmeResources/FETwo.PNG)

### Stretch Goals

There is still more functionality that I would aim to implement, including:

*	The ability to tag a dream with more than one theme
*	A filter system whereby clicking on a particular tag will return all dreams that share that same tag
*	Add options to sort and filter entries by specific criteria
* A password protected login page

I would also like to make the application more accessible by using a more readable colour scheme and incorporating more HTML accessbility elements.
