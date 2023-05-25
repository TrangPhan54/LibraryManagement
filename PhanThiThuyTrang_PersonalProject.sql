drop table if exists customer
create table customer (
customer_id int8 primary key not null ,
customer_firstName varchar(100) not null,
customer_lastName varchar(100) not null,
customer_address varchar(100) not null,
customer_phoneNumber varchar (25) not null,
)
insert into customer (customer_id,customer_firstName,customer_lastName ,
customer_address ,customer_phoneNumber)
values 
(1,	'Shauna','Banbrook','6 Packers Parkway', '5907999069'),
(2,	'Evin',	'Gonneau',	'2491 Oak Way',	'9991969678'),
(3,	'Michaelina','Petrosian','95 Dwight Park', '4443985726'),
(4,	'Gabbey','Davydychev',	'736 Lotheville Point',	'4712771785'),
(5,	'Rosabel','Dundon','1627 Erie Alley',	'2977244602'),
(6,	'Eunice','Yoslowitz','580 Mendota Junction', '2251713031'),
(7,	'Blancha','Yves',	'855 Reinke Alley',	'1548044673'),
(8,	'Michelle',	'Maleney',	'36 Thackeray Alley', '9727966533'),
(9,	'Cathy','Fogt',	'41 Monument Street',	'4004721474'),
(10,'Kitty','Cabrara',	'6 Hintze Parkway',	'5045305331'),
(11, 'Marisa',	'Matthieson','9939 Messerschmidt Place',	'8358703990'),
(12,'Barbara-anne',	'Duddan',	'435 Hudson Crossing',	'3549858965'),
(13,'Baudoin','Barfoot','4 3rd Alley',	'4577004136'),
(14,'Honey','Hanks','35426 Hagan Parkway',	'4036514335'),
(15,'Hernando',	'Kosiada','6 Hazelcrest Center',	'1822992545'),
(16,'Dasi',	'Semor','4 Forster Hill', '7352116729'),
(17,'Desiri','Avieson','03065 Buell Street', '7478844398'),
(18,'Berkie','Ipgrave',	'606 Namekagon Plaza', '1443561316'),
(19,'Natalie','McCormack',	'808 Hoffman Park', '2277837691'),
(20,'Tait',	'Just',	'24103 Hermina Street',	'9378638801')


create table account (
user_id int primary key not null,
customer_id int not null,
user_email varchar(50) not null,
user_password varchar(50) not null)

insert into account (user_id,customer_id,user_email,user_password)
values
(1,	1,	'sbanbrook0@comcast.net',	'vbM928zzv5'),
(2,	2,	'egonneau1@smh.com.au',	'XzLfrkxvx5'),
(3,	3,	'mpetrosian2@delicious.com'	, 'Y0smGKTkTg'),
(4,  4,	'gdavydychev3@sbwire.com',	'hRQSmcllB7'),
(5,	5,	'rdundon4@hostgator.com',	'Bp5xF3YWu'),
(6,	6,	'eyoslowitz5@merriam-webster.com',	'lZB1bO'),
(7,	7,	'byves6@skype.com'	,'H4xkhqCJclmD'),
(8,	8,	'mmaleney7@ox.ac.uk',	'zhcvH7c'),
(9,	9,	'cfogt8@yandex.ru',	'JmOmfZNXt50'),
(10,	10,	'kcabrara9@histats.com'	,'ry2X6zPexP'),
(11,	11,	'mmatthiesona@woothemes.com'	,'IS6nUQgTzn8'),
(12,	12,	'bduddanb@a8.net',	'lQJ3DMOw'),
(13,	13,	'bbarfootc@dedecms.com'	,'ttdK4h'),
(14,	14,	'hhanksd@ucoz.ru'	,'9mfLGl'),
(15,	15,	'hkosiadae@foxnews.com',	'r3RslQoe5'),
(16,	16,	'dsemorf@hhs.gov',	'GEhjVnWE2'),
(17,	17,	'daviesong@nps.gov',	'IaLQAp'),
(18,	18,	'bipgrave11@google.com.au'	,'ARCYgGk4JFtO'),
(19,	19,	'nmccormack12@angelfire.com',	'a4tHGef9'),
(20,	20,	'tjust13@joomla.org',	'Qn9mWZ6sNdn')

alter table account add constraint account_fk foreign key (customer_id) references customer (customer_id)

insert into account

create table roleOfUser(
role_id int primary key not null,
user_id int not null,
role_name varchar(50) not null)

create table assignment_role (
assignment_id int primary key  not null,
user_id int not null,
role_id int not null
)

alter table assignment_role add constraint assignment_role_fk foreign key (user_id) references account (user_id)
alter table assignment_role add constraint assignment_role_1_fk foreign key (role_id) references roleOfUser (role_id)



drop table if exists book

create table book (
book_id int8 primary key not null,
publishingHouse_id int8 not null,
bookName varchar(100) not null,
author_id int8 not null,
book_image varchar(100) not null,
contentSummary varchar(250) not null,
datePublish date not null,
price int not null)


(1,1,	'gone with the wind',	1,	'https://e7.pngegg.com/pngimages/934/734/png-clipart-dvd-film-gone-with-the-wind-film-poster-film-thumbnail.png'	, 'The story is set in Clayton County and Atlanta, both in Georgia, during the American Civil War and Reconstruction Era. It depicts the struggles of young Scarlett OHara, the spoiled daughter of a well-to-do plantation owner, who must use every means at her disposal to claw her way out of poverty following Sherman s destructive "March to the Sea".'	'1980-01-01',150000),
(2,	2,	'the thorn bird',	2	,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSES3M93E9Al41F36OqZjUV-0L-nwuKVb1-Zdzs5KpQflcmDe9k6_0hgE0yNiC87VifKek&usqp=CAU','	The Thorn Birds traces the lives of the members of the Cleary family over the course of three generations, from their poor existence in New Zealand to their eventual move to Australia when a distant relative summons them and promises them a more stable life.'	,'1988-09-02',	130000),
(3,	3,	'norwegian wood',	3,	'https://m.media-amazon.com/images/I/A1-2Tn2UGlL._AC_UF1000,1000_QL80_.jpg',	'The novel is a nostalgic story of loss and burgeoning sexuality. It is told from the first-person perspective of Toru Watanabe, who looks back on his days as a college student living in Tokyo.Through Watanabe s reminiscences, readers see him develop relationships with two very different women—the beautiful yet emotionally troubled Naoko, and the outgoing, lively Midori',	'1990-04-12',	150000),
(4,	4,	'les misérables',	4,	'https://i.pinimg.com/736x/f6/e5/69/f6e569093f8c4ad8742423fb036b0e62--victor-hugo-les-mis%C3%A9rables.jpg',	'Set in the Parisian underworld and plotted like a detective story, the work follows the fortunes of the convict Jean Valjean, a victim of society who has been imprisoned for 19 years for stealing a loaf of bread. A hardened and streetwise criminal upon his release, he eventually softens and reforms, becoming a successful industrialist and mayor of a northern town. Despite this, he is haunted by an impulsive, regretted former crime and is pursued relentlessly by the police inspector Javert. Valjean eventually gives himself up for the sake of his adopted daughter, Cosette, and her husband, Marius.','1880/06/07',	200000),
(5,	5,	'The Gadfly',	5,	'https://ae01.alicdn.com/kf/H63bc71e27849469bbb70b8513b5522f8T/The-Gadfly-Full-Irish-authoress-Ethel-Lilian-Voynich-English-Edition-Novel-Fiction-Classic-Literature-Kids-Novels.jpg',	'The Gadfly is set in Italy during the revolutionary ferment of the 1830s and 40s. The protagonist is a young Englishman, Arthur Burton, who moves to a Catholic seminary after the death of his mother. There he becomes devoted to his guardian and mentor Montanelli, who, unknown to him, is also his biological father.','1990-10-09',	99000),
(6,	6,	'Sans Famille',	6,	'https://image.jimcdn.com/app/cms/image/transf/none/path/s603e23ff941fd5f3/image/i589129ec3fb145f2/version/1523369846/image.jpg',	'The story tells about an adventurous adventure of Rémi - a boy with no parents, no relatives who live with his adoptive mother in a remote countryside. Rémi is cared for in the loving embrace of Mother Barberin. Until one day, her husband working in Paris returned from an accident with disabled legs, he insisted on taking Rémi with the circus of old Vitalis - an experienced and virtuous old man, walking traveling and touring all over France. His circus includes three clever dogs: Capi, Zerbino, Dolce and "general" the sly monkey Joli - Coeu. Since the beginning of the journey with Mr. Vitalis and his "friends", Rémi has experienced many turbulent life, ups and downs, when he was sad but was finally able to smile happily.',	'1999-05-24',	80000),
(7,	7,	'Q&A',	7,	'https://d3fa68hw0m2vcc.cloudfront.net/335/268836206.jpeg',	'It tells the rags to riches story of Ram Mohammad Thomas, a young waiter who becomes the biggest quiz show winner in history, only to be arrested and jailed on accusations that he cheated. His lawyer is the only thing standing between him and the producers and police attempt to force a false confession that would deprive him of the prize. The only way to prove his innocence is by sharing the episodes of his life and travels in India which explain where and how he learned the answers to the quiz show questions.',	'1998-08-07',	95000),
(8,	8,	'the count of monte cristo',	8,	'https://i.pinimg.com/originals/ed/8a/6c/ed8a6c97cbfaa26ee22d9df844b16cd9.jpg',	'The Count of Monte Cristo follows the protagonist, Edmund Dantes, who is betrayed and falsely imprisoned on the eve of his wedding. While imprisoned, Dantes learns the location of the treasure on The Island of Monte Cristo. Dantes escapes from the prison and uses the treasure to become the Count of Monte Cristo',	'1980-12-07',	160000),
(9,	9,	'dreamy eyes',	9,	'https://www.vinabook.com/images/thumbnails/product/240x/324349_p85771mscan0001.jpg',	'Dreamy Eyes tells the story of Ngan, who falls in love with Hà Lan when they were both 6 years old. As they grow older, we follow the blossoming love Ngan has for Hà Lan, until she eventually moves to the big city to go to high school. Hà Lan takes to the big city a little too much, falling for Ngan’s rich cousin, only for him to break her heart and leave her pregnant. Throughout all of this, Ngan’s love for Hà Lan hasn’t wavered as he takes care of her during her pregnancy, eventually taking care of Hà Lan’s daughter Trà Long, as they both move back to the village, her to live with Hà Lan’s mother, and Ngan, having graduated, to teach in the small village school.'	, '1997-03-03',	70000),
(10,10,	'arch of triumph',	10,	'https://m.media-amazon.com/images/I/81fPBabRvAL._AC_UF1000,1000_QL80_.jpg',	'The novel is set in Paris, in 1939. Despite having no permission to perform surgery, stateless refugee Ravic, a very accomplished German surgeon, has been “ghost-operating” on patients for two years on the behalf of two less-skillful French physicians.Unwilling to return to Nazi Germany, which has stripped him of his citizenship, and unable to exist legally anywhere else in pre-war western Europe, Ravic manages to hang on. He is one of many displaced persons, without passports or any other documents, who live under a constant threat of being captured and deported from one country to the next, and back again.Ravic has given up on the possibility of love, but life has a curious way of taking a turn for the romantic, even during the worst of times.'	,'1940-12-12',	110000),
(11,11,	'shadows in paradise', 	11,	'https://archive.org/services/img/shadowsinparadis0000rema/full/pct:200/0/default.jpg',	'It is about a journalist, Robert Ross, who spent two years evading the Holocaust hiding in an art museum, flees from Europe to the United States and settles in New York. He meets a woman named Natasha, begins a new career as an art dealer and travels to Hollywood. After the war is over, Ross eventually leaves the States.'	3/3/1945	80000
(12	,12	,'The hunchback of Notre Dame',	12,	'https://static.wikia.nocookie.net/notre-dame-de-paris/images/c/c8/51mm-qw36VL.jpg/revision/latest?cb=20230326195050',	'It is the story of Quasimodo, the deformed bell-ringer of the Notre Dame Cathedral, who falls in love with the beautiful gypsy Esmeralda. When Esmeralda is condemned as a witch by Claude Frollo, the tormented archdeacon who lusts after her, Quasimodo attempts to save her; but his intentions are misunderstood.'	,6/7/1930	,120000),
(13,13,	'socrate in love', 	13,	'https://media.instocktrades.com/large/NOV073919.jpg',	'In a small town of southern Japan, Sakutaro Saku Matsumoto and Aki Hirose, classmates since junior high, become high school students. During this time they begin to date and their conversations circle around the idea of what love really is, beginning after Saku s grandfather shares his own past love story with Saku.
After a trip the two take to an abandoned island, Aki discovers she has leukemia, which limits her chances to go outside or see Saku. Once Saku learns the truth, he buys flight tickets to take Aki to Australia s Uluru (Ayers Rock); a place she had always wanted to visit after missing the school trip there, but she dies before she could board the plane.
The story takes place as a flashback through Sakus eyes as he and Aki''s parents travel to Australia to spread Aki''s ashes in the place she had always wanted to see.', '1985-04-08',	50000),
(14,	14,	'be with you',	14	,'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/hostedimages/1485137250i/21787424.jpg', 'When Takumi''s wife suddenly returns from the grave, he can''t believe his eyes. As he starts digging deeper and deeper into the mystery of her sudden reappearance, he discovers a secret that is somehow linked to the past...and the future. Is it possible to experience first love for a second time? Without question, the answer is yes!',
, '1988-09-07',	60000),
(15,	15,	'If Only It Were True',	15,	'https://i.pinimg.com/originals/df/e7/3b/dfe73bb639c92d484fe342405022155c.jpg',	'If Only it Were True is set against the backdrop of San Francisco and tells the story of Lauren Kline, a young, pretty, medical resident, completely devoted to her work in the emergency room of San Francisco Memorial Hospital. She works round-the-clock dealing with patients until she gets into a serious car accident. As a result of the accident, Lauren enters a coma. She "wakes" to awareness outside of her still comatose body and is frustrated to learn that she cannot communicate with anyone. She chooses to spend most of her time in her old apartment, where she is discovered by Arthur, the new tenant. Only he can see, hear, or touch her. After some initial disbelief on his part, Arthur agrees to help Lauren.'	,'1998-12-31',	70000),
(16,	16,	'call me by your name', 	16,	'https://media.thuprai.com/__sized__/products/Call_me_by_your_name-thumbnail-280x405-70.jpg',	'Details the love story of Elio and Oliver, two young men who spend a summer together on the Italian Riviera and develop a bond that shapes their view of love for the rest of their lives. Elio is a precocious 17-year-old who spends summers with his family in their villa on the Italian Riviera. Oliver is a brilliant and handsome 24-year-old post-doctoral scholar from America who spends a summer in the mid-1980s at Elio''s villa. Elio''s parents select Oliver to live with them for six weeks as part of an annual fellowship that they offer to young scholars, with the purpose of helping them revise a manuscript for publication and aiding Elio''s father—who is himself an academic—with his paperwork. Call Me By Your Name is a story about obsessive love, the passage of time, and life-defining memories.',	'1997-01-04',	98000),
(17,	17,	'garden of words',	17,	'https://play-lh.googleusercontent.com/I2BD6dScP_Lg44iVGh3UTABPPTrikrYFA5hV6K-x3IKxTFmfZKywAVgnXxxlJU_nX7LLJw=w240-h480-rw',	'On a rainy morning in Tokyo, Takao Akizuki, an aspiring shoemaker, decides to skip class to sketch designs in a beautiful garden. This is where he meets Yukari Yukino, a beautiful yet mysterious woman, for the very first time. Offering to make her new shoes, Takao continues to meet with Yukari throughout the rainy season, and without even realizing it, the two are able to alleviate the worries hidden in their hearts just by being with each other. However, their personal struggles have not disappeared completely, and as the end of the rainy season approaches, their relationship will be put to the test.'	, '1995-10-10',	75000),
(18,	18,	'kane and abel',	18,	'https://covers.openlibrary.org/b/id/11279372-L.jpg',	'Two strangers born worlds apart with one destiny that will define them both.
William Lowell Kane, the son of a Boston millionaire, and Abel Rosnovski, the son of a penniless Polish immigrant, are born on the same day on opposite sides of the world and brought together by fate and the quest of a dream.
Locked in a relentless struggle spanning sixty years and three generations, the two men battle for supremacy in pursuit of an empire, fuelled only by their hatred for the other and the knowledge it will end in triumph for one, and destruction of the other','1980-09-26',	120000),
(19,19,	'a woman is no man', 	19,	'https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/JR5DJ3BW44I6TJAA4SA36JSP3Q.jpg',	'This debut novel by a Palestinian-American voice takes us inside the lives of a conservative Arab family living in America. In Brooklyn, eighteen-year-old Deya is starting to meet with suitors. Though she doesn’t want to get married, her grandparents give her no choice. History is repeating itself: Deya’s mother, Isra, also had no choice when she left Palestine as a teenager to marry Adam. Though Deya was raised to believe her parents died in a car accident, a secret note from a mysterious, yet familiar-looking woman makes Deya question everything she was told about her past. As the narrative alternates between the lives of Deya and Isra, she begins to understand the dark, complex secrets behind her community.','1999-05-25',	100000),
(20,20,	'the diary of anne frank',	20,	'https://m.media-amazon.com/images/I/81xPFVVGesL._AC_UF1000,1000_QL80_.jpg','It tells the story of her family who live in Frankfurt, Germany and suddenly have to go into hiding as a result of Hitler and the Nazi Party’s treatment of Jews in Europe during the second world war. They escape to Amsterdam where they go into hiding with other Jews. The diary ends suddenly on 1 August 1944.','1955-04-24',	85000)

drop table if exists orderBook

create table orderBook (
order_id int8 primary key not null,
customer_id int8 not null,
orderingDate date not null,
address varchar(100) not null,
status_deliver int4 not null)

(1,1,'2023-21-04','6 Packers Parkway',0)
(2,2,'2023-21-04','2491 Oak Way',1)
(3,3,'2023-21-04','95 Dwight Park',1)
(4,4,'2023-21-04','736 Lotheville Point',2)
(5,5,'2023-21-04','1627 Erie Alley',1)
(6,6,'2023-21-04','580 Mendota Junction',3)
(7,7,'2023-21-04','855 Reinke Alley',1)
(8,8,'2023-21-04','36 Thackeray Alley',1)
(9,9,'2023-21-04','41 Monument Street',2)
(10,10,'2023-21-04','6 Hintze Parkway',3)
(11,11,'2023-21-04','9939 Messerschmidt Place',0)
(12,12,'2023-21-04','435 Hudson Crossing',1)
(13,13,'2023-21-04','4 3rd Alley',1)
(14,14,'2023-21-04','35426 Hagan Parkway',1)
(15,15,'2023-21-04','6 Hazelcrest Center',2)
(16,16,'2023-21-04','4 Forster Hill',0)
(17,17,'2023-21-04','03065 Buell Street',2)
(18,18,'2023-21-04','606 Namekagon Plaza',1)
(19,19,'2023-21-04','808 Hoffman Park',0)
(20,20,'2023-21-04','24103 Hermina Street',1)
(21,1,'2023-21-04','6 Packers Parkway',1)
(22,2,'2023-21-04','2491 Oak Way',2)
(23,3,'2023-21-04','95 Dwight Park',2)
(24,4,'2023-21-04','736 Lotheville Point',0)
(25,5,'2023-21-04','1627 Erie Alley',0)
(26,6,'2023-21-04','580 Mendota Junction',1)
(27,7,'2023-21-04','855 Reinke Alley',0)
(28,8,'2023-21-04','36 Thackeray Alley',3)
(29,9,'2023-21-04','41 Monument Street',2)
(30,10,'2023-21-04','6 Hintze Parkway',1)
(31,11,'2023-21-04','9939 Messerschmidt Place',0)
(32,12,'2023-21-04','435 Hudson Crossing',1)
(33,13,'2023-21-04','4 3rd Alley',1)
(34,14,'2023-21-04','35426 Hagan Parkway',0)
(35,15,'2023-21-04','6 Hazelcrest Center',0)
(36,16,'2023-21-04','4 Forster Hill',2)
(37,17,'2023-21-04','03065 Buell Streett',3)
(38,18,'2023-21-04','606 Namekagon Plaza',2)
(39,19,'2023-21-04','808 Hoffman Park',1)
(40,20,'2023-21-04','24103 Hermina Street',1)
(41,1,'2023-21-04','24103 Hermina Street',3)
(42,2,'2023-21-04','24103 Hermina Street',3)
(43,3,'2023-21-04','24103 Hermina Street',0)
(44,4,'2023-21-04','24103 Hermina Street',1)
(45,5,'2023-21-04','24103 Hermina Street',1)
(46,6,'2023-21-04','24103 Hermina Street',2)
(47,7,'2023-21-04','24103 Hermina Street',2)
(48,8,'2023-21-04','24103 Hermina Street',2)
(49,9,'2023-21-04','24103 Hermina Street',3)
(50,10,'2023-21-04','24103 Hermina Street',0)





alter table orderBook add constraint orderBook_fk foreign key (customer_id) references customer (customer_id)

drop table if exists genre

create table genre (
genre_id int primary key not null,
genreName varchar(100) not null)

(1, 'Classic'),
(2, 'Drama'),
(3, 'Fairytale'),
(4, 'Fantasy'),
(5, 'Graphic novel'),
(6, 'Historical fiction'),
(7, 'Mystery'),
(8, 'Paranormal romance'),
(9, 'Poetry'),
(10, 'Romance'),
(11, 'Short story'),
(12, 'Thriller'),
(13, 'Western'),
(14, 'Young adult'),
(15, 'Journal'),
(16, 'Math'),
(17 , 'Memoir'),
(18, 'Philosophy'),
(19, 'Prayer'),
(20, 'Religion, spirituality, and new age')

drop table if exists genre_book

create table genre_book (
genre_book_id int8 primary key not null,
book_id int8 not null,
genre_id int8 not null)

alter table genre_book add constraint genre_book_fk foreign key (book_id) references book (book_id)
alter table genre_book add constraint genre_book_1_fk foreign key (genre_id) references genre (genre_id)





drop table if exists response

create table response (
response_id int primary key not null,
customer_id int not null,
book_id int not null,
response_content varchar(300))


alter table response add constraint response_fk foreign key (customer_id) references customer (customer_id)
alter table response add constraint response_1_fk foreign key (book_id) references book (book_id)


drop table if exists orderDetail

create table orderDetail (
ordering_detail_id int primary key not null,
order_id int not null,
book_id int not null,
price_book int not null,
quantityOfBooks int not null)

alter table orderDetail add constraint orderDetail_fk foreign key (order_id) references orderBook (order_id)
alter table orderDetail add constraint orderDetail_1_fk foreign key (book_id) references book (book_id)



drop table if exists author

create table author (
author_id int primary key not null,
author_firstName varchar(200) not null,
author_lastName varchar(200) not null
)
insert into author 
values 
(1,	'Margaret','Mitchell'),
(2,	'Colleen','Mc Cullough'),
(3,	'Haruki','Murakami'),
(4,	'Victor','Hugo'),
(5,	'Ethel','Voynich'),
(6,	'Hector' ,'Mallot'),
(7,	'Vikas','Swarup'),
(8,	'Alexandre','Dumas'),
(9,	'Anh','Nguyen Nhat'),
(10,'Erich','Maria Remarque'),
(11	,'Kyoichi',	'Katayama'),
(12	,'Ichikawa','Takuji'),
(13	,'Marc','Levy'),
(14	,'André','Aciman'),
(15	,'shinkai',	'makoto'),
(16	,'Jeffrey' ,'Archer'),
(17	,'Etaf','Rum'),
(18	,'Anne','Frank')

drop table if exists author_book

create table author_book (
author_book_id  int primary key not null,
book_id int not null,
author_id int not null)

alter table author_book add constraint author_book_fk foreign key (book_id) references book (book_id)
alter table author_book add constraint author_book_1_fk foreign key (author_id) references author (author_id)


drop table if exists publishingHouse

create table publishingHouse (
publishingHouse_id  int primary key not null,
publishingHouse_name varchar(300) not null
)

insert into publishinghouse 
values 
(1,	'Nha xuat ban van hoc'),
(2,	'Nha xuat ban phu nu Viet Nam'),
(3,	'Nha xuat ban Kim Dong'),
(4,	'Nha xuat ban Tre'),
(5,	'Nha xuat ban Van Hoa Thong Tin'),
(6,	'Nha xuat ban Van Nghe'),
(7,	'Nha xuat ban Cong An'),
(8,	'Nha xuat ban Da Nang'),
(9,	'Nha Nam'),
(10,'Dong A'),
(11,'Dinh Ti Books'),
(12,'Nha xuat ban tong hop Thanh Pho Ho Chi Minh'),
(13,'Nha xuat ban Hoi Nha Van'),
(14,'Sky Books'),
(15,'Penguin Books'),
(16, 'Nha xuat ban Ha Noi'),
(17,'Nha xuat ban Hong Duc')
(18,'Nha xuat ban Lao Dong'),
(19, 'Nha xuat ban Tri Thuc'),
(20, 'Nha xuat ban Thanh Nien')


drop table if exists book_publish

create table book_publish (
book_publish_id  int primary key not null,
book_id int not null,
publishingHouse_id int not null)

alter table book_publish add constraint book_publish_fk foreign key (book_id) references book (book_id)
alter table book_publish add constraint book_publish_1_fk foreign key (publishingHouse_id) references publishingHouse (publishingHouse_id)



create table book1 (
order_id int8 primary key not null,
book varchar(100)  not null,
genre varchar(100) not null);

INSERT INTO "PersonalProject".book1 (order_id,book,genre) VALUES
	 (1,'Action and adventure','Art/architecture'),
	 (2,'Alternate history','Autobiography'),
	 (3,'Anthology','Biography'),
	 (4,'Chick lit','Business/economics'),
	 (5,'Children''s','Crafts/hobbies'),
	 (6,'Classic','Cookbook'),
	 (7,'Comic book','Diary'),
	 (8,'Coming-of-age','Dictionary');
