-- College table
CREATE TABLE IF NOT EXISTS college (
  college_id VARCHAR(64) PRIMARY KEY,
  college_name VARCHAR(100) NOT NULL
);

-- Classes table
CREATE TABLE IF NOT EXISTS classes (
  class_id VARCHAR(64) PRIMARY KEY,
  class_name VARCHAR(100) NOT NULL,
  coach VARCHAR(50),
  coach_call VARCHAR(20),
  college_id VARCHAR(64),
  grade VARCHAR(20)
);

-- Manager table
CREATE TABLE IF NOT EXISTS manager (
  manager_id VARCHAR(64) PRIMARY KEY,
  manager_name VARCHAR(50) NOT NULL,
  manager_password VARCHAR(100) NOT NULL,
  manager_call VARCHAR(20)
);

-- Apartment table
CREATE TABLE IF NOT EXISTS apartment (
  apartment_id VARCHAR(64) PRIMARY KEY,
  apartment_name VARCHAR(100) NOT NULL,
  sex VARCHAR(10),
  manager_id VARCHAR(64),
  total_floor INT DEFAULT 1,
  total_people INT DEFAULT 0
);

-- Bedroom table
CREATE TABLE IF NOT EXISTS bedroom (
  bedroom_id VARCHAR(64) PRIMARY KEY,
  apartment_id VARCHAR(64),
  bedroom_name VARCHAR(100) NOT NULL,
  status VARCHAR(2) DEFAULT 'N',
  total_bed VARCHAR(20) DEFAULT '0/4'
);

-- Student table
CREATE TABLE IF NOT EXISTS student (
  student_id VARCHAR(64) PRIMARY KEY,
  student_name VARCHAR(50) NOT NULL,
  student_password VARCHAR(100) NOT NULL DEFAULT '123456',
  sex VARCHAR(10),
  class_id VARCHAR(64),
  class_name VARCHAR(100),
  status VARCHAR(2) DEFAULT 'N',
  bedroom_id VARCHAR(64),
  bedroom_name VARCHAR(100),
  student_no VARCHAR(50),
  grade VARCHAR(20),
  college_id VARCHAR(64),
  college_name VARCHAR(100)
);

-- Announcement table
CREATE TABLE IF NOT EXISTS announcement (
  announcement_id VARCHAR(64) PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  content TEXT,
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Visitor table
CREATE TABLE IF NOT EXISTS visitor (
  visitor_id VARCHAR(64) PRIMARY KEY,
  visitor_name VARCHAR(50) NOT NULL,
  visitor_sex VARCHAR(10),
  visitor_call VARCHAR(20),
  bedroom_name VARCHAR(100),
  reason VARCHAR(500),
  visitor_date VARCHAR(50)
);
