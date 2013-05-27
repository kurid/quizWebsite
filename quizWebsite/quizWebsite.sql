
use quizWebsite;

create dataBase quizWebsite;

DROP TABLE IF EXISTS accounts;

create table accounts(
	accountID int primary key ,
	nick varchar(64),
	name varchar(64),
	surname varchar(64),
	password varchar(64),
	mail varchar(64),
	Achievements varchar(64)
);


create table friendships(
	accountID1 int,
	accountID2 int,
	foreign key (accountID1) references accounts (accountID),
	foreign key (accountID2) references accounts (accountID)
);


create table friendRequests(
	accountIdTo int,
	accountIdFrom int,
	foreign key (accountIDTo) references accounts (accountID),
	foreign key (accountIDFrom) references accounts (accountID)
);


create table quizes(
	quizID int primary key,
	authorID int,
	name varchar(64),
	quiz_date date, 
	description varchar(1024),
	foreign key (authorID) references accounts (accountID)
);


create table questions(
	questionID int primary key,
	type int,
	questionText varchar(512),
	score int
);


-- pasuxebic aq weria
create table multipleChoice(
	questionID int,
	answer varchar(64),
	isCorrect bool,
	foreign key (questionID) references questions (questionID)
);



create table answers(
	questionID int,
	answer varchar(64),
	foreign key (questionID) references questions (questionID)
);

create table matching(
	questionID int,
	answerId1 int,
	answerId2 int,
	foreign key (questionID) references questions (questionID)
);


create table tags(
	quizID int,
	tagType int,
	foreign key (quizID) references quizes (quizID)
);


-- num sheidzleba saxeli shevucvalot
create table questionToQuiz(
	questionToQuizID int,
	quizID int,
	questionID int,
	num int,
	foreign key (quizID) references quizes (quizID),
	foreign key (questionID) references questions (questionID)
);



create table challenges(
	accountIdTo int,
	accountIdFrom int,
	quizID int,
	foreign key (accountIDTo) references accounts (accountID),
	foreign key (accountIDFrom) references accounts (accountID),
	foreign key (quizID) references quizes (quizID)	
);


create table massages(
	accountIdTo int,
	accountIdFrom int,
	text varchar(64),
	read_unread bool,
	foreign key (accountIDTo) references accounts (accountID),
	foreign key (accountIDFrom) references accounts (accountID)
);


create table takenQuizes(
	accountID int,
	quizID int,
	total_score int,
	quiz_time time,
	quiz_date date,
	foreign key (accountID) references accounts (accountID),
	foreign key (quizID) references quizes (quizID)
);