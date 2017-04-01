		CREATE TABLE ESRBRating (
                shortName  VARCHAR(2),
                 ratingName VARCHAR(20) NOT NULL UNIQUE,  
                 PRIMARY KEY (shortName)) ;
				 
         CREATE TABLE RegisteredUser 
                 (userName  VARCHAR(20), 
                  email     VARCHAR(50) NOT NULL UNIQUE, 
                  password  VARCHAR(12) NOT NULL, 
                 PRIMARY KEY (userName)) ;
         
		 CREATE TABLE Admin  
                 (userName  VARCHAR(20), 
                  email     VARCHAR(50) NOT NULL UNIQUE, 
                  password  VARCHAR(20) NOT NULL, 
                 PRIMARY KEY (userName), 
                 FOREIGN KEY (userName) REFERENCES RegisteredUser 
                  ON DELETE CASCADE) ;
         
		 CREATE TABLE Game 
                 (gameID        INTEGER, 
                  addedBy       VARCHAR(20), 
                  ESRBrating    VARCHAR(2) NOT NULL, 
                  name          VARCHAR(100) NOT NULL, 
                 PRIMARY KEY (gameID), 
                 FOREIGN KEY (addedBy) REFERENCES Admin(userName) 
                  ON DELETE SET NULL, 
                 FOREIGN KEY (ESRBrating) REFERENCES ESRBRating(shortName) 
                  ON DELETE CASCADE) ;
         
		 CREATE TABLE Release  
                 (gameID        INTEGER, 
                  region        VARCHAR(20), 
                  platform      VARCHAR(20), 
                  addedBy       VARCHAR(20), 
                  releaseDate   DATE,  
                 PRIMARY KEY (gameID, region, platform), 
                 FOREIGN KEY (gameID) REFERENCES Game 
                  ON DELETE CASCADE,  
                 FOREIGN KEY (addedBy) REFERENCES Admin(userName) 
                  ON DELETE SET NULL) ;
         
		 CREATE TABLE CreateReview 
                 (reviewID      INTEGER, 
                  userName      VARCHAR(20) NOT NULL, 
                  gameID        INTEGER NOT NULL, 
                  region        VARCHAR(20), 
                  platform      VARCHAR(20)NOT NULL,  
                  reviewText    VARCHAR(1000),  
                  postDateTime  TIMESTAMP, 
                  rating        FLOAT(1), 
                 PRIMARY KEY (reviewID), 
                 FOREIGN KEY (userName) REFERENCES RegisteredUser 
                  ON DELETE CASCADE, 
                 FOREIGN KEY (gameID) REFERENCES Game 
                  ON DELETE CASCADE) ;
         
		 CREATE TABLE Tag 
                 (name      VARCHAR(20), 
                  addedBy   VARCHAR(20), 
                 PRIMARY KEY (name), 
                 FOREIGN KEY (addedBy) REFERENCES Admin(userName) 
                  ON DELETE SET NULL) ;
         
		 CREATE TABLE TagGame 
                 (tagName   VARCHAR(50), 
                  gameID    INTEGER, 
                  userName  VARCHAR(20), 
                 PRIMARY KEY (tagName, userName, gameID), 
                 FOREIGN KEY (tagName) REFERENCES Tag 
                  ON DELETE CASCADE, 
                 FOREIGN KEY (gameID) REFERENCES Game 
                  ON DELETE CASCADE, 
                 FOREIGN KEY (userName) REFERENCES RegisteredUser 
                  ON DELETE SET NULL) ;
         
		 CREATE TABLE List  
                 (listID    INTEGER, 
                  name      VARCHAR(50) NOT NULL, 
                  userName  VARCHAR(20) NOT NULL, 
                 PRIMARY KEY (listID),  
                 FOREIGN KEY (userName) REFERENCES RegisteredUser 
                  ON DELETE CASCADE) ;
         
		 CREATE TABLE ListEntries 
                 (listID    INTEGER, 
                  gameID    INTEGER, 
                  dateAdded DATE,  
                 PRIMARY KEY (listID, gameID), 
                 FOREIGN KEY (listID) REFERENCES List 
                  ON DELETE CASCADE, 
                 FOREIGN KEY (gameID) REFERENCES Game 
                  ON DELETE CASCADE) ;

         INSERT INTO ESRBRATING VALUES ('EC', 'Early Childhood'); 
		 INSERT INTO ESRBRATING VALUES ('E', 'Everyone'); 
		 INSERT INTO ESRBRATING VALUES ('E10+', 'Everyone 10+'); 
		 INSERT INTO ESRBRATING VALUES ('T', 'Teen'); 
		 INSERT INTO ESRBRATING VALUES ('M', 'Mature'); 
		 INSERT INTO ESRBRATING VALUES ('AO', 'Adults Only'); 
              
  


INSERT INTO RegisteredUser VALUES ('Zelda','princess@hyrule.org','wisdom');
INSERT INTO RegisteredUser VALUES ('Cloud777','cloud9@shinra.com','aeris');
INSERT INTO RegisteredUser VALUES ('SamusPrime','saaamus@warmmail.com','p0w3rp@ck');
INSERT INTO RegisteredUser VALUES ('LCroft','lcroft@croftmanor.com','moreraiding');
INSERT INTO RegisteredUser VALUES ('PuckMan','pcman@pmail.com','wakka');
INSERT INTO RegisteredUser VALUES ('Yoshi','yoshi@mk.com','cookies!');
INSERT INTO RegisteredUser VALUES ('RealBowser','fakebowser@mk.com','peach');
INSERT INTO RegisteredUser VALUES ('GLadOS','glados@aperture.org','cake');
INSERT INTO RegisteredUser VALUES ('CmdrShepard','shepard@n7.com','normandy');
INSERT INTO RegisteredUser VALUES ('Kefka','kefka@ffvi.org','sand');


INSERT INTO Admin VALUES ('Yoshi','yoshi@mk.com','cookies!');
INSERT INTO Admin VALUES ('RealBowser','fakebowser@mk.com','peach');
INSERT INTO Admin VALUES ('GLadOS','glados@aperture.org','cake');
INSERT INTO Admin VALUES ('CmdrShepard','shepard@n7.com','normandy');
INSERT INTO Admin VALUES ('Kefka','kefka@ffvi.org','sand');


INSERT INTO Tag VALUES ('Indie','CmdrShepard');
INSERT INTO Tag VALUES ('Story Rich','GLadOS');
INSERT INTO Tag VALUES ('Great Soundtrack','GLadOS');
INSERT INTO Tag VALUES ('Horror','CmdrShepard');
INSERT INTO Tag VALUES ('Co-Op','Yoshi');



INSERT INTO Game VALUES (1,'RealBowser','E','Heros Duty 17');
INSERT INTO Game VALUES (2,'Yoshi','M','Yoshis Penninsula');
INSERT INTO Game VALUES (3,'GLadOS','T','Window');
INSERT INTO Game VALUES (4,'GLadOS','T','Window II');
INSERT INTO Game VALUES (5,'Yoshi','AO','Matter Consequence');


INSERT INTO TagGame VALUES ('Story Rich',5,'SamusPrime');
INSERT INTO TagGame VALUES ('Co-Op',5,'SamusPrime');
INSERT INTO TagGame VALUES ('Horror',2,'Zelda');
INSERT INTO TagGame VALUES ('Indie',3,'Zelda');
INSERT INTO TagGame VALUES ('Co-Op',1,'PuckMan');


INSERT INTO List VALUES (1,'Faves','Yoshi');
INSERT INTO List VALUES (2,'To play','PuckMan');
INSERT INTO List VALUES (3,'To Replay','PuckMan');
INSERT INTO List VALUES (4,'Best JRPGs','SamusPrime');
INSERT INTO List VALUES (5,'Top 4','Zelda');

INSERT INTO ListEntries VALUES (1,2,TO_DATE('2017-01-17 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (1,5,TO_DATE('2017-01-17 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (1,1,TO_DATE('2016-09-20 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (2,2,TO_DATE('2016-10-14 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (3,2,TO_DATE('2017-04-11 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (4,3,TO_DATE('2017-01-17 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (5,1,TO_DATE('2017-01-17 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (5,5,TO_DATE('2017-01-17 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (6,1,TO_DATE('2017-01-17 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (6,5,TO_DATE('2017-01-17 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO ListEntries VALUES (6,3,TO_DATE('2017-01-17 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));

INSERT INTO Review VALUES (1,'Zelda',1,'meh',To_Date('2017-01-23 12:13:04', 'YYYY-MM-DD HH24-MI-SS'),2.5);
INSERT INTO Review VALUES (2,'Zelda',2,'The best!',To_Date('2017-04-23 12:13:04', 'YYYY-MM-DD HH24-MI-SS'),5);
INSERT INTO Review VALUES (3,'Cloud777',5,'ZOMG',To_Date('2015-03-23 12:13:04', 'YYYY-MM-DD HH24-MI-SS'),4);
INSERT INTO Review VALUES (4,'SamusPrime',4,NULL,To_Date('2012-01-23 12:13:04', 'YYYY-MM-DD HH24-MI-SS'),4);
INSERT INTO Review VALUES (5,'Zelda',4,NULL,To_Date('2011-11-23 12:13:04', 'YYYY-MM-DD HH24-MI-SS'),5);


INSERT INTO Release VALUES (1,'NTSC','PlayStation 4','RealBowser',TO_DATE('2015-12-25 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO Release VALUES (1,'PAL','PlayStation 3','RealBowser',TO_DATE('2014-12-25 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO Release VALUES (4,'NTSC','PC','Yoshi',TO_DATE('2008-07-07 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO Release VALUES (3,'PAL','PC','GLadOS',TO_DATE('2005-04-14 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO Release VALUES (2,'PAL','GameCube','Yoshi',TO_DATE('2013-10-12 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));
INSERT INTO Release VALUES (5,'PAL','PC','Yoshi',TO_DATE('2013-10-12 00:00:00', 'YYYY-MM-DD HH24-MI-SS'));










