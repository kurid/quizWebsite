use quizWebsite;

DROP TABLE IF EXISTS friendships;
DROP TABLE IF EXISTS friendRequests;
DROP TABLE IF EXISTS multipleChoice;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS matching;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS questionToQuiz;
DROP TABLE IF EXISTS challenges;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS takenQuizes;
DROP TABLE IF EXISTS quizes;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS imageQuestion;
DROP TABLE IF EXISTS imigeQuestion;
DROP TABLE IF EXISTS questions;
DROP PROCEDURE IF EXISTS addFriend;
DROP PROCEDURE IF EXISTS removeFriend;
DROP PROCEDURE IF EXISTS addAnswer;
DROP FUNCTION IF EXISTS addQuestion;
DROP TABLE IF EXISTS tagToQuiz;
DROP TABLE IF EXISTS tags;

create table accounts(
	accountID int primary key AUTO_INCREMENT,
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
	foreign key (accountID1) references accounts (accountID)  ON DELETE CASCADE,
	foreign key (accountID2) references accounts (accountID)  ON DELETE CASCADE
);


create table friendRequests(
	accountIdTo int,
	accountIdFrom int,
	sendTime datetime,
	foreign key (accountIDTo) references accounts (accountID)  ON DELETE CASCADE,
	foreign key (accountIDFrom) references accounts (accountID) ON DELETE CASCADE
);


create table quizes(
	quizID int primary key AUTO_INCREMENT not null,
	authorID int,
	name varchar(64),
	quiz_create_date datetime, 
	description varchar(1024),
	UNIQUE KEY(authorID, name),
	foreign key (authorID) references accounts (accountID) ON DELETE CASCADE
);

create table questions(
	questionID int primary key AUTO_INCREMENT not null,
	type int,
	questionText varchar(512),
	score int,
	num int
);

-- pasuxebic aq weria
create table multipleChoice(
	questionID int,
	answer varchar(64),
	isCorrect bool,
	foreign key (questionID) references questions (questionID)  ON DELETE CASCADE
);

create table answers(
	questionID int,
	answer varchar(64),
	answerNum int,
	foreign key (questionID) references questions (questionID)  ON DELETE CASCADE
);

create table matching(
	questionID int,
	answer1 varchar(64),
	answer2 varchar(64),
	foreign key (questionID) references questions (questionID)  ON DELETE CASCADE
);

-- num sheidzleba saxeli shevucvalot
create table questionToQuiz(
	quizID int,
	questionID int,
	foreign key (quizID) references quizes (quizID)  ON DELETE CASCADE,
	foreign key (questionID) references questions (questionID)  ON DELETE CASCADE
);

create table challenges(
	accountIdTo int,
	accountIdFrom int,
	quizID int,
	sendTime datetime,
	foreign key (accountIDTo) references accounts (accountID)  ON DELETE CASCADE,
	foreign key (accountIDFrom) references accounts (accountID)  ON DELETE CASCADE,
	foreign key (quizID) references quizes (quizID)	 ON DELETE CASCADE
);


create table messages(
	accountIdTo int,
	accountIdFrom int,
	text varchar(64),
	read_unread bool,
	sendTime datetime,
	foreign key (accountIDTo) references accounts (accountID)  ON DELETE CASCADE,
	foreign key (accountIDFrom) references accounts (accountID)  ON DELETE CASCADE
);


create table takenQuizes(
	accountID int,
	quizID int,
	total_score int,
	quiz_time time,
	quiz_take_date datetime,
	foreign key (accountID) references accounts (accountID)  ON DELETE CASCADE,
	foreign key (quizID) references quizes (quizID)  ON DELETE CASCADE
);

create table imageQuestion(
	questionID int,
	url varchar(512),
	foreign key (questionID) references questions (questionID)  ON DELETE CASCADE
);

create table tags(
	tagID int primary key AUTO_INCREMENT not null,
	name varchar(32)
);

create table tagToQuiz(
	tagID int,
	quizID int,
	foreign key (quizID) references quizes (quizID)  ON DELETE CASCADE,
	foreign key (tagID) references tags (tagID)  ON DELETE CASCADE
);

-- addFriend PROCEDURE --
DELIMITER $$
CREATE PROCEDURE addFriend(id1 int, id2 int)
BEGIN
	INSERT INTO friendships(accountID1, accountID2) VALUES (id1, id2);
END$$
DELIMITER ;

-- removeFriend PROCEDURE --
DELIMITER $$
CREATE PROCEDURE removeFriend(id1 int, id2 int)
BEGIN
	Delete from friendships
	where (accountID1 = id1 and accountID2 = id2) or (accountID1 = id2 and accountID2 = id1);
END$$
DELIMITER ;

-- addQuestion FUNCTION --
DELIMITER $$
CREATE FUNCTION addQuestion(qType int, qText varchar(512), qScore int, qNum int)
returns int
BEGIN
	DECLARE last_insert_id int;
	INSERT INTO questions (type, questionText, score, num) VALUES(qType, qText, qScore, qNum);
	SET last_insert_id = LAST_INSERT_ID();
	return last_insert_id;
END$$
DELIMITER ;

-- addAnswer PROCEDURE --
DELIMITER $$
CREATE PROCEDURE addAnswer(nAnswer int, answer varchar(64), questionID int)
BEGIN
	INSERT INTO answers VALUES (questionID, answer, nAnswer);
END$$
DELIMITER ;


drop view if exists popularQuizes;

create VIEW popularQuizes as
select quizID, 
count(quizID) as count FROM takenquizes
GROUP BY quizID ORDER BY count desc limit 0,5;

drop view if EXISTS doneQuizzes;

create view doneQuizzes as
select accountID, quiz_time, quiz_take_date,total_score,
quizes.quizID, authorID, name, description
from takenquizes, quizes
where quizes.quizID = takenquizes.quizID;


drop view if EXISTS challengeQuiz;

create view challengeQuiz as
select accountIdTo,accountIdFrom,challenges.quizID, sendTime,authorID, name, quiz_create_date, description
from challenges, quizes
where challenges.quizID = quizes.quizID;