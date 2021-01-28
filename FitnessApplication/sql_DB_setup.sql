CREATE TABLE personaltrainer (
	trainerID INT,
	firstName VARCHAR(20),
	lastName VARCHAR(20),
	contact VARCHAR(20),
	PRIMARY KEY (trainerid)
); 

CREATE TYPE GOAL AS ENUM ('WEIGHTLOSS', 'MUSCLEBUILDING', 'COMPETITION'); 
CREATE TYPE TARGETMUSCLEGROUP AS ENUM ('CHEST', 'ARMS', 'LEGS', 'SHOULDERS', 'BACK');
	
CREATE TABLE client (
	memberID INT,
	firstName VARCHAR(20),
	lastName VARCHAR(20),
	contact VARCHAR(20),
	clientGoal GOAL, 
	trainerID INT,
	username VARCHAR(20),
	password VARCHAR(20),
	PRIMARY KEY (memberID),
	FOREIGN KEY (trainerID) REFERENCES personaltrainer(trainerID)
);
 

CREATE TABLE exercise (
	exerciseNumber INT,
	name VARCHAR(30),
	targetMuscle VARCHAR(30),
	targetmusclegroup TARGETMUSCLEGROUP,
	PRIMARY KEY (exerciseNumber)
);

CREATE TABLE workout (
	workoutID INT,
	name VARCHAR(40),
	targetMuscleGroup VARCHAR(40),
	memberID INT,
	programID INT,
	PRIMARY KEY (workoutID),
	FOREIGN KEY (memberID) REFERENCES client(memberID)
);

CREATE TABLE workoutComponent (
	workoutComponentID INT,
	exerciseNumber INT,
	workoutID INT,
	sets INT,
	reps INT,
	PRIMARY KEY (workoutComponentID)
);

CREATE TABLE program (
	programID INT,
	trainerID INT,
	name VARCHAR(30),
	PRIMARY KEY (programID)
);


INSERT INTO personaltrainer VALUES (3, 'Greg', 'Thompson', '043287463'); 

INSERT INTO client VALUES (000111, 'Peter', 'Griffin', '043223463', 'WEIGHTLOSS', NULL); 
INSERT INTO client VALUES (000112, 'Ranjith', 'Kulasekara', '043223464', 'WEIGHTLOSS', 3); 
INSERT INTO client VALUES (000113, 'John', 'Smith', '043223465', 'COMPETITION', 3); 
INSERT INTO client VALUES (000114, 'Usain', 'Bolt', '043223466', 'MUSCLEBUILDING', 3, 'usain', 'bolt'); 
INSERT INTO client VALUES (000115, 'Justin', 'Gatlin', '043223467', 'WEIGHTLOSS', 3); 
INSERT INTO client VALUES (000116, 'Yohan', 'Blake', '043223468', 'WEIGHTLOSS', 3); 
INSERT INTO client VALUES (000117, 'Andre', 'DeGrasse', '043223469', 'COMPETITION', 3); 
INSERT INTO client VALUES (000118, 'Peter', 'Griffin', '043223470', 'WEIGHTLOSS', 3); 

INSERT INTO client VALUES (000119, 'John', 'Steffensen', '12345678', 'WEIGHTLOSS', 3); 
INSERT INTO client VALUES (000120, 'John', 'Williams', '12345678', 'COMPETITION', 3); 
INSERT INTO client VALUES (000121, 'Kobe', 'Bryant', '12345678', 'MUSCLEBUILDING', 3); 
INSERT INTO client VALUES (000122, 'Kareem', 'Abdul-Jabbar', '12345678', 'WEIGHTLOSS', 3); 
INSERT INTO client VALUES (000123, 'Lebron', 'James', '12345678', 'WEIGHTLOSS', 3); 
INSERT INTO client VALUES (000124, 'Kevin', 'Durant', '12345678', 'COMPETITION', 3); 
INSERT INTO client VALUES (000125, 'Asafa', 'Powell', '12345678', 'WEIGHTLOSS', 3);

INSERT INTO client VALUES (000126, 'Christian', 'Coleman', '043223471', 'MUSCLEBUILDING', NULL); 


INSERT INTO exercise VALUES (1,'Barbell Shrug', 'Trapezius', 'BACK'); 
INSERT INTO exercise VALUES (2,'Dumbbell Shrug', 'Trapezius', 'BACK'); 
INSERT INTO exercise VALUES (3,'Smith Machine Shrug',  'Trapezius', 'BACK'); 
INSERT INTO exercise VALUES (4,'Standing Military Press', 'Anterior Deltoid', 'SHOULDERS');  
INSERT INTO exercise VALUES (5,'Side Laterals', 'Lateral Deltoid', 'SHOULDERS'); 
INSERT INTO exercise VALUES (6,'Reverse Flyes', 'Posterior Deltoid', 'SHOULDERS'); 
INSERT INTO exercise VALUES (7,'Standing Military Press', 'Anterior Deltoid', 'SHOULDERS'); 
INSERT INTO exercise VALUES (8,'Dumbbell Flyes', 'Inner Pectoralis Major', 'CHEST'); 
INSERT INTO exercise VALUES (9,'Incline Dumbbell Press', 'Upper Pectoralis Major', 'CHEST'); 
INSERT INTO exercise VALUES (10,'Decline Dumbbell Flyes', 'Lower Pectoralis Major', 'CHEST'); 

INSERT INTO workout VALUES (1, 'Armmageddon', 'ARMS', 111, NULL);
INSERT INTO workout VALUES (2, 'Armmageddon', 'ARMS', 112, NULL);
INSERT INTO workout VALUES (3, 'Armmageddon', 'ARMS', 113, NULL);
INSERT INTO workout VALUES (4, 'Armmageddon', 'ARMS', 114, NULL);
INSERT INTO workout VALUES (5, 'Posterior', 'LEGS', 114, 1);
INSERT INTO workout VALUES (6, 'Armmageddon', 'ARMS', 115, NULL);
INSERT INTO workout VALUES (7, 'Armmageddon', 'ARMS', 116, NULL);
INSERT INTO workout VALUES (8, 'Armmageddon', 'ARMS', 117, NULL);

INSERT INTO workoutComponent VALUES (1, 1, 1, 3, 10);
INSERT INTO workoutComponent VALUES (2, 1, 5, 3, 10);

INSERT INTO program VALUES (1, 3, 'Sample Program')