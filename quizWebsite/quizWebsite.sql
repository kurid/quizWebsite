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
DROP TABLE IF EXISTS imigeQuestion;
DROP TABLE IF EXISTS questions;


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
	quiz_date datetime, 
	description varchar(1024),
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


create table tags(
	quizID int,
	tagType int,
	foreign key (quizID) references quizes (quizID)  ON DELETE CASCADE
);


-- num sheidzleba saxeli shevucvalot
create table questionToQuiz(
	questionToQuizID int,
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
	quiz_date datetime,
	foreign key (accountID) references accounts (accountID)  ON DELETE CASCADE,
	foreign key (quizID) references quizes (quizID)  ON DELETE CASCADE
);




create table imigeQuestion(
	questionID int,
	url varchar(512),
	foreign key (questionID) references questions (questionID)  ON DELETE CASCADE
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