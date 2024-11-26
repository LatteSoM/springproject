CREATE TABLE Department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Тип документа
CREATE TABLE DocumentType (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Должность
CREATE TABLE Position (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES Department(id) ON DELETE CASCADE
);

-- Роль
CREATE TABLE Role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Пользователь
CREATE TABLE "User" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES Role(id) ON DELETE SET NULL
);

-- Сотрудник
CREATE TABLE Employee (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    date_hired DATE NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "User"(id) ON DELETE CASCADE
);

-- Позиция сотрудника
CREATE TABLE EmployeePosition (
    id SERIAL PRIMARY KEY,
    employee_id INT NOT NULL,
    position_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE,
    FOREIGN KEY (position_id) REFERENCES Position(id) ON DELETE CASCADE
);

-- Документ
CREATE TABLE Document (
    id SERIAL PRIMARY KEY,
    employee_id INT NOT NULL,
    document_type_id INT NOT NULL,
    document_number VARCHAR(255) NOT NULL,
    issue_date DATE NOT NULL,
    expiry_date DATE,
    FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE,
    FOREIGN KEY (document_type_id) REFERENCES DocumentType(id) ON DELETE CASCADE
);

-- Добавление уникальности для номера документа
CREATE UNIQUE INDEX idx_document_number ON Document(document_number);

-- Проект
CREATE TABLE Project (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_by_id INT,
    FOREIGN KEY (created_by_id) REFERENCES "User"(id) ON DELETE SET NULL
);

-- Задача
CREATE TABLE Task (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    project_id INT NOT NULL,
    assigned_to_id INT,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES Project(id) ON DELETE CASCADE,
    FOREIGN KEY (assigned_to_id) REFERENCES Employee(id) ON DELETE SET NULL
);

-- Расчет заработной платы
CREATE TABLE Payroll (
    id SERIAL PRIMARY KEY,
    employee_id INT NOT NULL,
    year INT NOT NULL,
    month INT NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    bonuses DECIMAL(10, 2),
    deductions DECIMAL(10, 2),
    net_salary DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE
);

-- Время работы
CREATE TABLE WorkTime (
    id SERIAL PRIMARY KEY,
    employee_id INT NOT NULL,
    date DATE NOT NULL,
    hours_worked DECIMAL(5, 2) NOT NULL,
    description TEXT,
    FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE
);